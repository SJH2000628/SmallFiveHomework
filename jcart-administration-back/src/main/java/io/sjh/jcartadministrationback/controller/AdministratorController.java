package io.sjh.jcartadministrationback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.sjh.jcartadministrationback.constant.ClientExceptionConstant;
import io.sjh.jcartadministrationback.dto.in.*;
import io.sjh.jcartadministrationback.dto.out.*;
import io.sjh.jcartadministrationback.exception.ClientException;
import io.sjh.jcartadministrationback.po.Administrator;
import io.sjh.jcartadministrationback.service.AdministratorService;
import io.sjh.jcartadministrationback.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
@CrossOrigin
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

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
    public AdministratorGetProfileOutDTO getProfile(@RequestParam(required = false) Integer adminstratorId){

        return null;
    }

    @PostMapping("/updateProdfile")
    public void updateProdfile(@RequestBody AdministratorUpdateProfileInDTO administratorUpdateProfileInDTO){

     }
     /*
     *獲取重置嗎
     *  */
     @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email){
        return null;
     }
     @PostMapping("/resetPwd")
     public void resetPwd(@RequestBody AdministratorRestPwdInDTO administratorRestPwdInDTO){

     }

     @GetMapping("/getlist")
    public PageOutDTO<AdministratorListOutDTO> getlist(@RequestParam Integer pageNum){
        return null;
     }

     @GetMapping("/getById")
     public AdministratorShowOutDTO getById(@RequestParam Integer administratorId){
         return null;
     }

     @PostMapping("/create")
     public Integer create(@RequestBody AdministratorCreateInDTO administratorCreateInDTO){
         return null;
     }
     @PostMapping("/update")
    public void update(@RequestBody AdministratorUpdateInDTO administratorUpdateInDTO){

     }

     @PostMapping("/delete")
     public void delete(@RequestParam Integer adminstratorId){

     }

     @PostMapping("/batchDelete")
    public void batchDelete(@RequestParam List<Integer> adminstratorIds){

     }
}
