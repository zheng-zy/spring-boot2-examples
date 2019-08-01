//package com.zzy.dao.dbsong;
//
//import com.zzy.entity.SongFile;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
///**
// * <p></p>
// * Created by @author zhezhiyong@163.com on 2018/11/28.
// */
//public interface CommonDataDAO {
//
//    void saveSongFile(SongFile songFile);
//
//    int updateSongFileMd5(SongFile songFile);
//
//    SongFile getSongFileById(@Param("id") Integer id);
//
//    List<SongFile> getByFileTypeAndCompanyCode(@Param("fileType") Integer fileType, @Param("companyCode") String companyCode);
//
//    SongFile getByFileName(@Param("fileName") String fileName, @Param("companyCode") String companyCode);
//
//    /**
//     * 根据商家号和过滤类型获取歌曲ID或者歌手ID
//     */
//    List<String> getFilterSong(@Param("companyCode") String companyCode, @Param("filterType") String filterType);
//
//    /**
//     * 获取需要生成商家禁播歌曲歌手的商家
//     */
//    List<String> getFilterCompany();
//
//    /**
//     * 获取商家禁播信息
//     */
//    SongFile getSongFile(@Param("companyCode") String companyCode);
//
//}
