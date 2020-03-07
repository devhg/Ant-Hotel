package com.anthotel.face.service;

import com.anthotel.face.dto.FaceUserInfo;
import com.anthotel.face.dto.ProcessInfo;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.toolkit.ImageInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;


public interface FaceEngineService {

    void test();

    List<FaceInfo> detectFaces(ImageInfo imageInfo);

    List<ProcessInfo> process(ImageInfo imageInfo);

    /**
     * 人脸特征
     *
     * @param imageInfo
     * @return
     */
    byte[] extractFaceFeature(ImageInfo imageInfo) throws InterruptedException;

    /**
     * 人脸比对
     *
     * @param groupId
     * @param faceFeature
     * @return
     */
    List<FaceUserInfo> compareFaceFeature(byte[] faceFeature, Integer groupId) throws InterruptedException, ExecutionException;


    FaceSimilar comparePeopleIdCard(FaceFeature peopleFeature, FaceFeature idCardFeature);


}
