package com.zzia.graduation.base;

/**
 * 全局参数类，公共参数数据全部放到这个类里面进行引用
 * 
 * @author Administrator
 *
 */
public class Constants {

	public static class FileManager {
		// ************特别注意***********
		/*
		 * 说明一：配置tomcat
		 */
		// 需要在server.xml的host节点内配置以下代码，不然图片访问不到
		// <Context docBase="D:\CZF" path="/CZF/files"/>

		/*
		 * 说明二：分级依据
		 */
		// 关于文件路径的说明：1、按照层级来进行存储。 2、每一级都有一个默认的文件夹。 3、如果找不到对应文件夹，对于较少的文件，
		// 直接存在对应层级默认文件夹即可； 对于大批量的文件，应该创建对应的路径，存储到对应的文件夹下，【需要添加对应type值】。
		//

		/*
		 * 说明三：如何添加新的模块
		 */
		// 第一步：在该FileManager中按照下方规则定义模块以及模块的分层文件夹。
		// 第二步：按照下方规则，定义type值，
		// 第四步：去FileManagerAction的intercepted（）方法中进行拦截判断
		// 第五步：将新添加的新模块定义到接口文档
		/**
		 * 共用
		 */
		private final static String photoPath = "/photoPath";
		private final static String voicePath = "/voicePath";
		private final static String vedioPath = "/vedioPath";
		private final static String defaultPath = "/defaultPath";
		public final static String tempPath = "/thumbPath";
		// 默认缩略图片，如果生成缩略图片出错，直接用该图片替换
		public final static String defaultThumbImg = "http://avatar.csdn.net/C/A/9/1_wgyscsf.jpg";
		// 项目根url
		public final static String url_prject = "http://localhost/CZF";
		// 文件根url
		public final static String url_files = "http://localhost/CZF";
		// 缓存目录
		public static String fileTempPath = "D:/CZF/file/temp/path";
		/**
		 * 这里由管理员配置文件存放的根目录盘符，不要动名称，只可以更改盘符
		 */
		// // 一级目录
		// public static String rootPath = "D:/CZF";

		// 外网一级目录
		public static String rootPath = "D:/SZCZF/czf_file";
		// 二级目录
		public static String userPath = "";
		/**
		 * 三级级目录
		 */
		// 用户信息相关的根目录
		private final static String userRootPath = rootPath + userPath + "/userRootPath";// 用户相关文件存放位置
		// 各种任务相关的根目录
		private final static String taskRootPath = rootPath + userPath + "/taskRootPath";
		// 发现相关
		private final static String discoveryRootPath = rootPath + userPath + "/discoveryRootPath";

		// 网站相关
		private final static String websiteRootPath = rootPath + userPath + "/websiteRootPath";

		/**
		 * 四级目录
		 */
		// 悬赏任务
		private final static String rewardTaskRootPath = taskRootPath + "/rewardTaskRootPath";
		// 定向任务
		private final static String orientTaskRootPath = taskRootPath + "/orientTaskRootPath";
		// 呼叫服务
		private final static String callServiceRootPath = taskRootPath + "/callServiceRootPath";
		// 通知公告
		private final static String noticeTaskRootPath = taskRootPath + "/noticeTaskRootPath";

		// 专家
		private final static String expertsRootPath = discoveryRootPath + "/expertsRootPath";
		// 研究院
		private final static String academyRootPath = discoveryRootPath + "/academyRootPath";
		// 更多组织
		private final static String moreOrganizationRootPath = discoveryRootPath + "/moreOrganizationRootPath";

		// 荣誉资历
		private final static String HonorRootPath = websiteRootPath + "/HonorRootPath";
		// 产品
		private final static String ProductRootPath = websiteRootPath + "/ProductRootPath";
		// 成果
		private final static String ResultRootPath = websiteRootPath + "/ResultRootPath";
		// 案例
		private final static String CaseRootPath = websiteRootPath + "/CaseRootPath";
		/**
		 * 四级目录：访问路径如下，上面的为私有的，不允许访问
		 */
		public final static String rootAccessPath = "/files";// 访问，这是根路径.不要项目名称
		// 默认文件夹，一般不用，对于没有指定存放位置的，存放在该处
		public final static String rootDefaultPath = rootPath + userPath + defaultPath;

		// def
		public final static String userDefalutRootPath = userRootPath + defaultPath;// 默认存放位置
		public final static String userHeadImgRootPath = userRootPath + "/userHeadImgRootPath";// 用户头像存放位置
		public final static String userCredentialsRootPath = userRootPath + "/userCredentialsRootPath";// 证件照存放位置

		// def
		public final static String taskDefaultRootPath = taskRootPath + defaultPath;// 任务默认根目录
		public final static String rewardTaskRootPath_photoPath = rewardTaskRootPath + photoPath;// 悬赏任务
		public final static String rewardTaskRootPath_voicePath = rewardTaskRootPath + voicePath;
		public final static String rewardTaskRootPath_vedioPath = rewardTaskRootPath + vedioPath;
		public final static String rewardTaskRootPath_defaultPath = rewardTaskRootPath + defaultPath;
		public final static String orientTaskRootPath_photoPath = orientTaskRootPath + photoPath;// 定向任务
		public final static String orientTaskRootPath_voicePath = orientTaskRootPath + voicePath;
		public final static String orientTaskRootPath_vedioPath = orientTaskRootPath + vedioPath;
		public final static String orientTaskRootPath_defaultPath = orientTaskRootPath + defaultPath;
		public final static String callServiceRootPath_photoPath = callServiceRootPath + photoPath;// 呼叫服务
		public final static String callServiceRootPath_voicePath = callServiceRootPath + voicePath;
		public final static String callServiceRootPath_vedioPath = callServiceRootPath + vedioPath;
		public final static String callServiceRootPath_defaultPath = callServiceRootPath + defaultPath;
		public final static String noticeTaskRootPath_photoPath = noticeTaskRootPath + photoPath;// 通知公告
		public final static String noticeTaskRootPath_voicePath = noticeTaskRootPath + voicePath;
		public final static String noticeTaskRootPath_vedioPath = noticeTaskRootPath + vedioPath;
		public final static String noticeTaskRootPath_defaultPath = noticeTaskRootPath + defaultPath;
		// def
		public final static String discoveryDefaultRootPath = discoveryRootPath + defaultPath;// 任务默认根目录
		public final static String expertsRootPath_photoPath = expertsRootPath + photoPath;// 专家组
		public final static String expertsRootPath_vedioPath = expertsRootPath + vedioPath;// 专家组
		public final static String expertsRootPath_defaultPath = expertsRootPath + defaultPath;// 专家组
		public final static String academyRootPath_photoPath = academyRootPath + photoPath;// 研究院
		public final static String academyRootPath_vedioPath = academyRootPath + vedioPath;// 研究院
		public final static String academyRootPath_defaultPath = academyRootPath + defaultPath;// 研究院
		public final static String moreOrganizationRootPath_photoPath = moreOrganizationRootPath + photoPath;// 更多组织
		public final static String moreOrganizationRootPath_vedioPath = moreOrganizationRootPath + vedioPath;// 更多组织
		public final static String moreOrganizationRootPath_defaultPath = moreOrganizationRootPath + defaultPath;// 更多组织

		// def
		public final static String websiteDefaultRootPath = websiteRootPath + defaultPath;// 网站
		public final static String HonorRootPath_photoPath = HonorRootPath + photoPath;// 荣誉资历图片
		public final static String HonorRootPath_defaultPath = HonorRootPath + defaultPath;// 荣誉资历默认

		public final static String ProductRootPath_photoPath = ProductRootPath + photoPath;// 荣誉资历图片
		public final static String ProductRootPath_defaultPath = ProductRootPath + defaultPath;// 荣誉资历默认

		public final static String ResultRootPath_photoPath = ResultRootPath + photoPath;// 荣誉资历图片
		public final static String ResultRootPath_defaultPath = ResultRootPath + defaultPath;// 荣誉资历默认

		public final static String CaseRootPath_photoPath = CaseRootPath + photoPath;// 荣誉资历图片
		public final static String CaseRootPath_vedioPath = CaseRootPath + vedioPath;// 荣誉资历图片
		public final static String CaseRootPath_defaultPath = CaseRootPath + defaultPath;// 荣誉资历默认

		// ************文件储存类型type***************
		// 文件上传，根据自己文件所在的模块，传递对应的tyep值。用法：get请求参数 type（int）
		public final static int type_root_default = 10;// 根路径，默认存放位置

		public final static int type_userHeadImg = 20;// 用户头像存储路径
		public final static int type_userCredentials = 30;// 用户证件照存储路径
		public final static int type_user_default = 40;// 用户相关，默认存放位置

		public final static int type_rewardTask_photo = 50;// 悬赏任务，图片存储路径
		public final static int type_rewardTask_voice = 60;// 悬赏任务，语音存储
		public final static int type_rewardTask_vedio = 70;// 悬赏任务，视频存储路径
		public final static int type_rewardTask_default = 80;// 悬赏任务，默认存储路径。【下同】

		public final static int type_orientTask_photo = 90;// 定向任务
		public final static int type_orientTask_voice = 100;
		public final static int type_orientTask_vedio = 110;
		public final static int type_orientTask_default = 120;

		public final static int type_callService_photo = 130;// 呼叫服务
		public final static int type_callService_voice = 140;
		public final static int type_callService_vedio = 150;
		public final static int type_callService_default = 160;

		public final static int type_noticeTask_photo = 170;// 通知公告
		public final static int type_noticeTask_voice = 180;
		public final static int type_noticeTask_vedio = 190;
		public final static int type_noticeTask_default = 200;

		public final static int type_task_default = 210;// 任务相关，默认存储路径

		public final static int type_expertsRootPath_photo = 220;// 专家组
		public final static int type_expertsRootPath_vedio = 230;
		public final static int type_expertsRootPath_default = 240;

		public final static int type_academyRootPath_photo = 250;// 研究院
		public final static int type_academyRootPath_vedio = 260;
		public final static int type_academyRootPath_default = 270;

		public final static int type_moreOrganization_photo = 280;// 更多组织
		public final static int type_moreOrganization_vedio = 290;
		public final static int type_moreOrganization_default = 300;

		public final static int type_discovery_default = 310;// discoverydefaultfiles

		// 网站相关
		public final static int type_honorRootPath_photo = 320;// 专家组
		public final static int type_honorRootPath_default = 330;

		public final static int type_productRootPath_photo = 340;// 专家组
		public final static int type_productRootPath_default = 350;

		public final static int type_resultRootPath_photo = 360;// 专家组
		public final static int type_resultRootPath_default = 370;

		public final static int type_caseRootPath_photo = 380;// 专家组
		public final static int type_caseRootPath_vedio = 390;// 专家组
		public final static int type_caseRootPath_default = 400;

		public final static int type_website_default = 410;
		// 文件相关字符串信息
		public final static String err_mkDirs = "严重错误，文件夹创建失败(系统级错误)。";
		public final static String err_uploadFile = "严重错误，上传文件过程出现错误(不能继续其它操作，操作终止)";
		public final static String err_delFile = "普通错误，删除文件失败，找不到指定文件。(可以继续进行其它操作)";
		// 缩略图key,返回图片集合时，顺便返回图片缩略图集合，所对应的缩略图集合的key
		public final static String thumb_key = "thumb_key";
		// 图片缩略图前缀
		public final static String thumb_pre = "thumb_";

		// 待删除文件类型key
		public static final String KEY_PIC = "KEY_PIC";
		public static final String KEY_VEDIO = "KEY_VEDIO";
		public static final String KEY_FILE = "KEY_FILE";

		public static final String KEY_PIC_THUMB = "KEY_PIC_THUMB";
		public static final String KEY_VEDIO_THUMB = "KEY_VEDIO_THUMB";
	}

	public static class Code {
		// 错误
		public static final int ERROR = 500;
		// 正确
		public static final int SUCCESS = 200;
		// 数据为空
		public static final int EMPTY = 400;
		// 数据已存在
		public static final int EXIST = 401;
		// 参数错误
		public static final int PARAMSERROR = 402;
		// 用户已在其他终端登录，在线中
		public static final int ONLINE = 403;
		// 获取缩略图失败
		public static final int THUMBERROR = 502;
	}

	public static class Key {
		// 表示数据
		public static final String RESULT = "data";
		// 表示连接状态，在捕获到异常的时候为连接失败。
		public static final String RESULTCODE = "resultCode";
		// 返回描述
		public static final String MESSAGE = "message";
		// 表示数据状态
		public static final String STATUS = "status";

	}

	public static class CommonObjects {
		// 字符串截取长度
		public static final int SUBLENGTH = 60;

	}
}
