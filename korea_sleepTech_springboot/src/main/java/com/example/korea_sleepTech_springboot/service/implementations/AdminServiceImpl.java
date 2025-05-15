package com.example.korea_sleepTech_springboot.service.implementations;

import com.example.korea_sleepTech_springboot.common.ResponseMessage;
import com.example.korea_sleepTech_springboot.dto.admin.reponse.PromoteToAdminResponseDto;
import com.example.korea_sleepTech_springboot.dto.admin.request.PromoteToAdminRequestDto;
import com.example.korea_sleepTech_springboot.dto.response.ResponseDto;
import com.example.korea_sleepTech_springboot.entity.Role;
import com.example.korea_sleepTech_springboot.entity.User;
import com.example.korea_sleepTech_springboot.repository.RoleRepository;
import com.example.korea_sleepTech_springboot.repository.UserRepository;
import com.example.korea_sleepTech_springboot.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public ResponseDto<PromoteToAdminResponseDto> promoteUserToAdmin(PromoteToAdminRequestDto dto) {
        PromoteToAdminResponseDto data = null;

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException(ResponseMessage.NOT_EXISTS_USER));

        Role adminRole = roleRepository.findByRoleName("Admin")
                .orElseGet(() -> roleRepository.save(Role.builder().roleName("Admin").build()));

        boolean alreadyHasAdmin = user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN"));

        if (alreadyHasAdmin) {
            throw new IllegalStateException("이미 ADMIN 권한을 가지고 있습니다.");
        }

        user.getRoles().add(adminRole);

        User saveUser = userRepository.save(user);

        List<String> roleList = saveUser.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        data = new PromoteToAdminResponseDto(
                user.getEmail()
                , roleList
                , "권한변경이 정상적으로 이루어졌습니다."
        );

        return null;
    }
}
