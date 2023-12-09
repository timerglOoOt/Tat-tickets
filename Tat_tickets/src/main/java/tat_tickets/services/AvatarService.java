package tat_tickets.services;

import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;

public interface AvatarService {
    UserDto updateAvatar(UserDto userDto, FileInfo fileInfo);
    FileInfo getAvatarById(Integer avatarId);
    void deleteAvatarById(Integer avatarId);
}
