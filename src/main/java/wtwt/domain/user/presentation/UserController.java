package wtwt.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wtwt.common.doc.swagger.UserSwagger;
import wtwt.domain.user.presentation.dto.request.CheckEmailDuplicateReq;
import wtwt.domain.user.presentation.dto.request.SignUpReq;
import wtwt.domain.user.presentation.dto.request.UpdateUserReq;
import wtwt.domain.user.presentation.dto.response.CheckDuplicateRes;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    @PostMapping
    public ResponseEntity<Void> signup(SignUpReq request) {
        return null;
    }

    @PostMapping("/email/check")
    public ResponseEntity<CheckDuplicateRes> checkEmailDuplicate(CheckEmailDuplicateReq request) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, UpdateUserReq request) {
        return null;
    }
}
