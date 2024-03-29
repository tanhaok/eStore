package com.e.store.auth.services;

import com.e.store.auth.entity.Account;
import com.e.store.auth.viewmodel.req.SignInVm;
import com.e.store.auth.viewmodel.req.SignUpVm;
import com.e.store.auth.viewmodel.res.AuthResVm;
import com.e.store.auth.viewmodel.res.ValidateAuthVm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface IAuthService {

  ResponseEntity<HttpStatus> signUp(SignUpVm signUpData);

  Account loadAccountByUsername(String username);

  ResponseEntity<AuthResVm> signIn(SignInVm signInData);

  ResponseEntity<HttpStatus> activeAccount(String token, String email);

  ResponseEntity<ValidateAuthVm> validateAuth();
}
