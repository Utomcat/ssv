package com.ranyk.ssv.backup;

import com.ranyk.ssv.backup.utils.MysqlBackupRestoreUtils;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@SpringBootTest
class BackupApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 数据库备份测试
     */
    @Test
    void backupTest() {
        Map<String, String> dataBaseMap = generateDataBaseData();
        log.error("数据备份开始.... ");
		try {
			MysqlBackupRestoreUtils.backup( dataBaseMap.get("host"),  dataBaseMap.get("userName"),  dataBaseMap.get("password"),
					 dataBaseMap.get("backupFolderPath"),  dataBaseMap.get("fileName"),  dataBaseMap.get("database"));
		} catch (IOException | InterruptedException e) {
			log.error("数据备份失败!!");
			log.error("数据备份失败错误信息: " + e.getMessage());
		}
		log.error("数据备份成功!");
	}

    /**
     * 数据库还原测试
     */
	@Test
    void restoreTest(){
        Map<String, String> dataBaseMap = generateDataBaseData();
        log.error("数据还原开始.... ");
        try {
            MysqlBackupRestoreUtils.restore( dataBaseMap.get("restoreFilePath"),  dataBaseMap.get("host"),  dataBaseMap.get("userName"),
                    dataBaseMap.get("password"),  dataBaseMap.get("database"));
        } catch (IOException | InterruptedException e) {
            log.error("数据还原失败!!");
            log.error("数据还原失败错误信息: " + e.getMessage());
        }
        log.error("数据还原成功!");
    }

    /**
     * 生成数据还原和备份的数据库信息
     *
     * @return 返回数据备份和还原的数据库信息的 Map 集合
     */
    private Map<String, String> generateDataBaseData() {
        Map<String, String> dataBase = new HashMap<>(16);
        dataBase.put("userName", "root");
        dataBase.put("database", "mango");
        dataBase.put("host", "localhost");
        dataBase.put("password", "123456");
        dataBase.put("fileName", "mango.sql");
        dataBase.put("backupFolderPath", "E:/Work/IdeaWorkSpace/ssv/ssv-admin/logs");
        dataBase.put("restoreFilePath", "E:/Work/IdeaWorkSpace/ssv/ssv-admin/logs/mango.sql");
        return dataBase;
    }

}
