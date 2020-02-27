package io.sjh.jcartadministrationback.controller;

import io.sjh.jcartadministrationback.dto.in.AdministratorLoginInDTO;
import io.sjh.jcartadministrationback.dto.in.AdministratorRestPwdInDTO;
import io.sjh.jcartadministrationback.dto.in.AdministratorUpdateProfileInDTO;
import io.sjh.jcartadministrationback.dto.out.AdministratorGetProfileOutDTO;
import io.sjh.jcartadministrationback.dto.out.AdministratorListOutDTO;
import io.sjh.jcartadministrationback.dto.out.PageOutDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/administrator")
public class AdministratorController {
    @GetMapping("/login")
    public String login(AdministratorLoginInDTO administratorLoginInDTO){

        return null;
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
}
