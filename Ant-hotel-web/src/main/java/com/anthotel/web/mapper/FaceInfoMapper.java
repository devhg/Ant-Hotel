package com.anthotel.web.mapper;

import com.anthotel.face.dto.UserFaceInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author: Devhui
 * @Date: 2020/3/7 22:35
 * @Email: devhui@ihui.ink
 * @Version: 1.0
 */

@Repository
public interface FaceInfoMapper {
    int insertUserFaceInfo(UserFaceInfo userFaceInfo);
}
