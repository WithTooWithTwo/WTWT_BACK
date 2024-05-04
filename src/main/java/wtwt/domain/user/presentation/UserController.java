package wtwt.domain.user.presentation;

import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wtwt.common.doc.swagger.UserSwagger;
import wtwt.domain.user.application.UserService;
import wtwt.domain.user.presentation.dto.request.CheckEmailDuplicateApiReq;
import wtwt.domain.user.presentation.dto.request.SignUpApiReq;
import wtwt.domain.user.presentation.dto.request.UpdateUserApiReq;
import wtwt.domain.user.presentation.dto.response.CheckDuplicateApiRes;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController implements UserSwagger {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody @Valid SignUpApiReq request) {
        Long id = userService.signUp(request.toSignUpReq());
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/users/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(uri)
            .build();
    }

    @PostMapping("/email/check")
    public ResponseEntity<CheckDuplicateApiRes> checkEmailDuplicate(
        @RequestBody @Valid CheckEmailDuplicateApiReq request) {
        CheckDuplicateApiRes response = CheckDuplicateApiRes.builder()
            .isDuplicated(userService.isDuplicatedEmail(request.email()))
            .build();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, UpdateUserApiReq request) {
        return null;
    }
}
