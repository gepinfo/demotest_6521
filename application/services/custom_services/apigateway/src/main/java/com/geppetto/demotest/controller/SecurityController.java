package com.geppetto.demotest.controller;

import com.geppetto.demotest.config.NewConstant;
import com.geppetto.demotest.apiAdapter.ApiAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.lang.Exception;
import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.jsonwebtoken.*;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class SecurityController {

    @Autowired
    private ApiAdapter apiAdapter;



@PostMapping("/signup")
public ResponseEntity<Object> signup(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : signup");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.post(NewConstant.SECURITYURL + "/signup",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : signup");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@PutMapping("/logout")
public ResponseEntity<Object> logout(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : logout");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.put(NewConstant.SECURITYURL + "/logout",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : logout");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@PostMapping("/googlesignin")
public ResponseEntity<Object> googleLogin(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : googleLogin");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.post(NewConstant.SECURITYURL + "/googlesignin",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : googleLogin");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@GetMapping("/getallusers")
public ResponseEntity<Object> getAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : getAllUser");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.get(NewConstant.SECURITYURL + "/getallusers",request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : getAllUser");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@GetMapping("/getuser/{id}")
public ResponseEntity<Object> getUserById( @PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : getUserById");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.get(NewConstant.SECURITYURL + "/getuser/"+id,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : getUserById");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@GetMapping("/getallroles")
public ResponseEntity<Object> getAllRole(HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : getAllRole");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.get(NewConstant.SECURITYURL + "/getallroles",request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : getAllRole");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@PutMapping("/updateuser")
public ResponseEntity<Object> updateUser(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : updateUser");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.put(NewConstant.SECURITYURL + "/updateuser",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : updateUser");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@PutMapping("/updateuserimg")
public ResponseEntity<Object> updateuserimg(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : updateuserimg");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.put(NewConstant.SECURITYURL + "/updateuserimg",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : updateuserimg");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@PostMapping("/saveroles")
public ResponseEntity<Object> saveRoles(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : saveRoles");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.post(NewConstant.SECURITYURL + "/saveroles",object,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : saveRoles");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@DeleteMapping("/deleteroles/{id}")
public ResponseEntity<Object> deleteRoles( @PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : deleteRoles");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.delete(NewConstant.SECURITYURL + "/deleteroles/"+id,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : deleteRoles");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

@DeleteMapping("/deleteuser/{id}")
public ResponseEntity<Object> deleteUser( @PathVariable String id,HttpServletRequest request, HttpServletResponse response) throws IOException{
        log.info("Enter into SecurityController : deleteUser");
        try{
            String servletPath = request.getContextPath();
                    switch (servletPath) {
                        case "/mobile":
                        case "/web":
          Object result= apiAdapter.delete(NewConstant.SECURITYURL + "/deleteuser/"+id,request);
          return ResponseEntity.ok(result);
          default:
                    String errorMessage = "Invalid servlet path: " + servletPath;
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
                    }
        }
        catch(Exception e){
        log.info("Exit into SecurityController : deleteUser");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }





        @PostMapping("login")
        public ResponseEntity<Object> login(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response)throws Exception{
        try{
        log.info("Enter into  SecurityController.java:login");
        String servletPath = request.getContextPath();
        switch (servletPath) {
        case "/mobile":
        case "/web":
        ResponseEntity<Object> result=apiAdapter.post(NewConstant.SECURITYURL + "/login" ,object,request);
        Map<String, Object> responseBody = (Map<String,Object>) result.getBody();
        String idToken = (String) responseBody.get("idtoken");

        if (idToken == null || idToken.isEmpty() || idToken == null) {
        switch (servletPath) {
        case "/mobile":
        return ResponseEntity.ok().body("Userdetails: " + result.getBody());
        case "/web":
        return ResponseEntity.ok().body("Userdetails: " + result.getBody());
        default:
        return ResponseEntity.ok().body(null);
        }
        }else{
        int i = idToken.lastIndexOf('.');
        String withoutSignature = idToken.substring(0, i+1);
        Jwt<Header,Claims> headerClaimsJwt = Jwts.parser().parseClaimsJwt(withoutSignature);

        Map<String, Object> user = new HashMap<>();
        user.put("firstname", headerClaimsJwt.getBody().get("firstName"));
        user.put("lastname", headerClaimsJwt.getBody().get("lastName"));
        user.put("listOfRoles", headerClaimsJwt.getBody().get("role"));
        user.put("id", headerClaimsJwt.getBody().get("id"));
        user.put("username", headerClaimsJwt.getBody().get("sub"));
        user.put("email", headerClaimsJwt.getBody().get("email"));
        ResponseEntity<Object> authProxyResponse= apiAdapter.post(NewConstant.AUTHPROXYURL + "/proxy" ,user,request);
        Map<String, Object> res = new HashMap<>();
        res.put("Access", authProxyResponse.getBody());
        res.put("Userdetails", user);
        return ResponseEntity.ok(res);
        }
        default:
        String errorMessage = "Invalid servlet path: " + servletPath;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        } catch (Exception e) {
        log.info("Exit from  SecurityController.java: login");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        }


        @PostMapping("consent")
        public ResponseEntity<Object> consent(@RequestBody Object object,HttpServletRequest request, HttpServletResponse response) throws Exception{
        try{
        log.info("Enter into SecurityController.java: consent");
        String servletPath = request.getContextPath();
        switch (servletPath) {
        case "/mobile":
        case "/web":
        ResponseEntity<Object> result=apiAdapter.post(NewConstant.SECURITYURL + "/login" ,object,request);
        Map<String, Object> responseBody = (Map<String, Object>) result.getBody();
        String token = (String) responseBody.get("idtoken");

        int i = token.lastIndexOf('.');
        String withoutSignature = token.substring(0, i+1);
        Jwt<Header,Claims> headerClaimsJwt = Jwts.parser().parseClaimsJwt(withoutSignature);

        Map<String, Object> user = new HashMap<>();
        user.put("firstname", headerClaimsJwt.getBody().get("firstName"));
        user.put("lastname", headerClaimsJwt.getBody().get("lastName"));
        user.put("listOfRoles", headerClaimsJwt.getBody().get("role"));
        user.put("id", headerClaimsJwt.getBody().get("id"));
        user.put("username", headerClaimsJwt.getBody().get("sub"));
        user.put("email", headerClaimsJwt.getBody().get("email"));

        ResponseEntity<Object> authProxyResponse= apiAdapter.post(NewConstant.AUTHPROXYURL + "/proxy" ,user,request);
        Map<String, Object> res = new HashMap<>();
        res.put("Access", authProxyResponse.getBody());
        res.put("Userdetails", user);;
        return ResponseEntity.ok(res);
        default:
        String errorMessage = "Invalid servlet path: " + servletPath;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        } catch (Exception e) {
        log.info("Exit from  SecurityController.java: consent");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        }


    }

