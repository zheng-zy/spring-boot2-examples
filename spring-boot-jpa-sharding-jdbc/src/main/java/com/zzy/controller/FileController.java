package com.zzy.controller;

import com.google.common.collect.ImmutableBiMap;
import com.zzy.db.entity.File;
import com.zzy.db.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p></p>
 * Created by @author zhezhiyong@163.com on 2018/12/25.
 */
@RestController
@RequestMapping("file")
@Slf4j
public class FileController {
    @Resource
    private FileRepository fileRepository;

    @RequestMapping("addFile")
    @ResponseBody
    public Map<String, Object> addFile(@RequestBody File file) {
        log.info("file:{}", file.toString());
        file = fileRepository.save(file);
        log.info("fileId:{}", file.getFileId());
        return ImmutableBiMap.of("code", 0, "data", file);
    }

    @RequestMapping("getFile")
    @ResponseBody
    public Map<String, Object> getFile(@RequestParam(required = false, defaultValue = "-1") long fileId, @RequestParam(required = false,defaultValue = "") String md5) {
        File file = null;
        if (fileId>0){
            file = fileRepository.getOne(fileId);
        }
        if (StringUtils.isNotEmpty(md5)){
            file = fileRepository.findFirstByMd5(md5);
        }
        return ImmutableBiMap.of("code", 0, "data", file);
    }


}
