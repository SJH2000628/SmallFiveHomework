package io.sjh.jcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.github.pagehelper.Page;
import io.sjh.jcartadministrationback.constant.ClientExceptionConstant;
import io.sjh.jcartadministrationback.dto.in.*;
import io.sjh.jcartadministrationback.dto.out.*;
import io.sjh.jcartadministrationback.enumeration.AdministratorStatus;
import io.sjh.jcartadministrationback.exception.ClientException;
import io.sjh.jcartadministrationback.po.Administrator;
import io.sjh.jcartadministrationback.service.AdministratorService;
import io.sjh.jcartadministrationback.util.EmailUtil;
import io.sjh.jcartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/administrator")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private SecureRandom secureRandom;

    @Autowired
    private EmailUtil emailUtil;

    @Value("${spring.mail.username}")
    private String fromEmail;

    private Map<String,String> emailPwdResetCodeMap = new HashMap<>();

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping("/login")
    public AdministratorLoginOutDTO login(AdministratorLoginInDTO administratorLoginInDTO) throws ClientException {
        Administrator administrator = administratorService.getByUsername(administratorLoginInDTO.getUsername());
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRCOOE, ClientExceptionConstant.ADMINISTRATOR_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encPwdDB = administrator.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(administratorLoginInDTO.getPassword().toCharArray(), encPwdDB);

        if (result.verified) {
            AdministratorLoginOutDTO customerLoginOutDTO = jwtUtil.issueToken(administrator);
            return customerLoginOutDTO;
        }else {
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PASSWORD_INVALID_ERRCOOE, ClientExceptionConstant.ADMINISTRATOR_PASSWORD_INVALID_ERRMSG);
        }
    }



    @GetMapping("/getProfile")
    public AdministratorGetProfileOutDTO getProfile(@RequestAttribute Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorGetProfileOutDTO administratorGetProfileOutDTO = new AdministratorGetProfileOutDTO();
        administratorGetProfileOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorGetProfileOutDTO.setUsername(administrator.getUsername());
        administratorGetProfileOutDTO.setRealName(administrator.getRealName());
        administratorGetProfileOutDTO.setEmail(administrator.getEmail());
        administratorGetProfileOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorGetProfileOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());

        return administratorGetProfileOutDTO;
    }

    @PostMapping("/updateProdfile")
    public void updateProdfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO,
                               @RequestAttribute Integer administratorId){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorId);
        administrator.setRealName(administratorUpdateProfileInDTO.getRealName());
        administrator.setEmail(administratorUpdateProfileInDTO.getEmail());
        administrator.setAvatarUrl(administratorUpdateProfileInDTO.getAvatarUrl());
        administratorService.update(administrator);

    }
    @PostMapping("/changePwd")
    public void changePwd(@RequestBody AdministratorChangePwdInDTO administratorChangePwdInDTO ,
                          @RequestAttribute Integer administratorId){

    }
    /*
     *獲取重置嗎
     *  */
    @GetMapping("/getPwdResetCode")
    public void getPwdResetCode(@RequestParam String email){
        byte[] bytes = secureRandom.generateSeed(3);
        String hex = DatatypeConverter.printHexBinary(bytes);
        emailUtil.send(fromEmail,email,"jcartt管理端管理員密碼重置",hex);
        // todo send messasge to MQ
        emailPwdResetCodeMap.put(email,hex);
    }
    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody AdministratorRestPwdInDTO administratorRestPwdInDTO) throws ClientException {
        String email = administratorRestPwdInDTO.getEmail();
        if (email == null){
            throw  new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRCOOE,ClientExceptionConstant.ADMINISTRATOR_PWDRESET_EMAIL_NONE_ERRMSG);
        }
        String innerResetCode = emailPwdResetCodeMap.get(email);
        if(innerResetCode == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCOOE_NONE_ERRCOOE,ClientExceptionConstant.ADMINISTRATOR_PWDRESET_INNER_RESETCOOE_NONE_ERRMSG);
        }
        String outerResetCode = administratorRestPwdInDTO.getResetCode();
        if (outerResetCode == null){
            throw  new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCOOE_NONE_ERRCOOE,ClientExceptionConstant.ADMINISTRATOR_PWDRESET_OUTER_RESETCOOE_NONE_ERRMSG);
        }
        if(!outerResetCode.equalsIgnoreCase(innerResetCode)){
            throw  new ClientException(ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCOOE_INVALID_ERRCOOE,ClientExceptionConstant.ADMINISTRATOR_PWDRESET_RESETCOOE_INVALID_ERRMSG);
        }
        Administrator administrator = administratorService.getByEmail(email);
        if (administrator == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRCOOE,ClientExceptionConstant.ADMINISTRATOR_EMAIL_NOT_EXIST_ERRMSG);
        }

        String newPwd = administratorRestPwdInDTO.getNewPwd();
        if (newPwd == null){
            throw new ClientException(ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRCODE, ClientExceptionConstant.ADMINISTRATOR_NEWPWD_NOT_EXIST_ERRMSG);
        }
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, newPwd.toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);
        administratorService.update(administrator);
        emailPwdResetCodeMap.remove(email);
    }

    @GetMapping("/getlist")
    public PageOutDTO<AdministratorListOutDTO> getlist(@RequestParam(required = false,defaultValue = "1") Integer pageNum){
        Page<Administrator> page = administratorService.getList(pageNum);
        List<AdministratorListOutDTO> administratorListOutDTOS = page.stream().map(administrator -> {
            AdministratorListOutDTO administratorListOutDTO = new AdministratorListOutDTO();
            administratorListOutDTO.setAdministratorId(administrator.getAdministratorId());
            administratorListOutDTO.setUsername(administrator.getUsername());
            administratorListOutDTO.setStatus(administrator.getStatus());
            administratorListOutDTO.setCreateTimestamp(administrator.getCreateTime().getTime());
            administratorListOutDTO.setRealName(administrator.getRealName());
            return administratorListOutDTO;
        }).collect(Collectors.toList());
        PageOutDTO<AdministratorListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageName(page.getPageNum());
        pageOutDTO.setList(administratorListOutDTOS);
        return pageOutDTO;
    }

    @GetMapping("/getById")
    public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
        Administrator administrator = administratorService.getById(administratorId);
        AdministratorShowOutDTO administratorShowOutDTO = new AdministratorShowOutDTO();
        administratorShowOutDTO.setAdministratorId(administrator.getAdministratorId());
        administratorShowOutDTO.setUsername(administrator.getUsername());
        administratorShowOutDTO.setRealName(administrator.getRealName());
        administratorShowOutDTO.setEmail(administrator.getEmail());
        administratorShowOutDTO.setAvatarUrl(administrator.getAvatarUrl());
        administratorShowOutDTO.setStatus(administrator.getStatus());

        return administratorShowOutDTO;
    }

    @PostMapping("/create")
    public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
        Administrator administrator = new Administrator();
        administrator.setUsername(administratorCreateInDTO.getUsername());
        administrator.setRealName(administratorCreateInDTO.getRealName());
        administrator.setEmail(administratorCreateInDTO.getEmail());
        administrator.setAvatarUrl(administratorCreateInDTO.getAvatarUrl());
        administrator.setStatus(administratorCreateInDTO.getStatus());
        administrator.setCreateTime(new Date());
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, administratorCreateInDTO.getPassword().toCharArray());
        administrator.setEncryptedPassword(bcryptHashString);
        Integer administratorId = administratorService.create(administrator);
        return administratorId;
    }
    @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){
        Administrator administrator = new Administrator();
        administrator.setAdministratorId(administratorUpdateInDTO.getAdministratorId());
        administrator.setRealName(administratorUpdateInDTO.getRealName());
        administrator.setEmail(administratorUpdateInDTO.getEmail());
        administrator.setStatus(administratorUpdateInDTO.getStatus());
        administrator.setAvatarUrl(administratorUpdateInDTO.getAvatarUrl());
        String password = administratorUpdateInDTO.getPassword();
        if(password!=null){
            String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
            administrator.setEncryptedPassword(bcryptHashString);
        }
        administratorService.update(administrator);
    }

    @PostMapping("/delete")
    public void delete(@RequestBody Integer adminstratorId){
        administratorService.delete(adminstratorId);
    }

    @PostMapping("/batchDelete")
    public void batchDelete(@RequestBody List<Integer> adminstratorIds){
        administratorService.batchDelete(adminstratorIds);
    }
}
