package tat_tickets.services.impl;

import tat_tickets.dao.FileInfoRepository;
import tat_tickets.dao.UserRepository;
import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.models.User;
import tat_tickets.services.AvatarService;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import java.util.Optional;

public class AvatarServiceImpl implements AvatarService {
    private final UserRepository userRepository;
    private final FileInfoRepository fileInfoRepository;
    private UserMapper userMapper = new UserMapperImpl();

    public AvatarServiceImpl(UserRepository userRepository, FileInfoRepository fileInfoRepository) {
        this.userRepository = userRepository;
        this.fileInfoRepository = fileInfoRepository;}

    @Override
    public UserDto updateAvatar(UserDto userDto, FileInfo fileInfo) {
        FileInfo savedFileInfo = fileInfoRepository.save(fileInfo);

        User user = User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .avatarId(savedFileInfo.getId())
                .build();

        userRepository.update(user);

        return userMapper.toDto(user);
    }

    @Override
    public FileInfo getAvatarById(Integer id) {
        Optional<FileInfo> fileInfo = fileInfoRepository.findById(id);
        return fileInfo.orElse(null);
    }

    @Override
    public void deleteAvatarById(Integer avatarId) {
        fileInfoRepository.delete(avatarId);
    }
}
