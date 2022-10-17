package org.springframework.security.oauth2.server.authorization.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 通过 code 换取网页授权 access_token 返回值
 *
 * @author xuxiaowei
 * @since 0.0.1
 * 
 * 
 */
@Data
public class WeiBoWebsiteTokenResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 */
	@JsonProperty("access_token")
	private String accessToken;

	/**
	 * access_token接口调用凭证超时时间，单位（秒）
	 */
	@JsonProperty("expires_in")
	private Integer expiresIn;

	@JsonProperty("remind_in")
	private String remindIn;

	private String uid;

	private String isRealName;

	private String error;

	@JsonProperty("error_code")
	private Integer errorCode;

	private String request;

	@JsonProperty("error_uri")
	private String errorUri;

	@JsonProperty("error_description")
	private String errorDescription;

	private UserInfo userInfo;

	@Data
	public static class UserInfo {

		@JsonProperty("is_teenager")
		private int isTeenager;

		@JsonProperty("favourites_count")
		private int favouritesCount;

		@JsonProperty("vplus_ability")
		private int vplusAbility;

		@JsonProperty("province")
		private String province;

		@JsonProperty("screen_name")
		private String screenName;

		@JsonProperty("id")
		private long id;

		@JsonProperty("geo_enabled")
		private boolean geoEnabled;

		@JsonProperty("like_me")
		private boolean likeMe;

		@JsonProperty("verified_contact_mobile")
		private String verifiedContactMobile;

		@JsonProperty("light_ring")
		private boolean lightRing;

		@JsonProperty("wenda_ability")
		private int wendaAbility;

		@JsonProperty("video_total_counter")
		private VideoTotalCounter videoTotalCounter;

		@JsonProperty("nft_ability")
		private int nftAbility;

		@JsonProperty("ecommerce_ability")
		private int ecommerceAbility;

		@JsonProperty("domain")
		private String domain;

		@JsonProperty("following")
		private boolean following;

		@JsonProperty("verified_contact_email")
		private String verifiedContactEmail;

		@JsonProperty("status")
		private Status status;

		@JsonProperty("follow_me")
		private boolean followMe;

		@JsonProperty("friends_count")
		private int friendsCount;

		@JsonProperty("pay_date")
		private String payDate;

		@JsonProperty("credit_score")
		private int creditScore;

		@JsonProperty("user_ability_extend")
		private int userAbilityExtend;

		@JsonProperty("gender")
		private String gender;

		@JsonProperty("pay_remind")
		private int payRemind;

		@JsonProperty("city")
		private String city;

		@JsonProperty("profile_url")
		private String profileUrl;

		@JsonProperty("brand_ability")
		private int brandAbility;

		@JsonProperty("super_topic_not_syn_count")
		private int superTopicNotSynCount;

		@JsonProperty("created_at")
		@JsonFormat(pattern = "E MMM dd HH:mm:ss '+0800' yyyy", locale = "en")
		private LocalDateTime createdAt;

		@JsonProperty("live_ability")
		private int liveAbility;

		@JsonProperty("bi_followers_count")
		private int biFollowersCount;

		@JsonProperty("cardid")
		private String cardid;

		@JsonProperty("is_teenager_list")
		private int isTeenagerList;

		@JsonProperty("verified_reason")
		private String verifiedReason;

		@JsonProperty("video_status_count")
		private int videoStatusCount;

		@JsonProperty("newbrand_ability")
		private int newbrandAbility;

		@JsonProperty("verified_level")
		private int verifiedLevel;

		@JsonProperty("urisk")
		private int urisk;

		@JsonProperty("star")
		private int star;

		@JsonProperty("status_total_counter")
		private StatusTotalCounter statusTotalCounter;

		@JsonProperty("has_service_tel")
		private boolean hasServiceTel;

		@JsonProperty("online_status")
		private int onlineStatus;

		@JsonProperty("block_app")
		private int blockApp;

		@JsonProperty("url")
		private String url;

		@JsonProperty("avatar_large")
		private String avatarLarge;

		@JsonProperty("planet_video")
		private int planetVideo;

		@JsonProperty("gongyi_ability")
		private int gongyiAbility;

		@JsonProperty("hardfan_ability")
		private int hardfanAbility;

		@JsonProperty("statuses_count")
		private int statusesCount;

		@JsonProperty("insecurity")
		private Insecurity insecurity;

		@JsonProperty("verified_source")
		private String verifiedSource;

		@JsonProperty("allow_all_act_msg")
		private boolean allowAllActMsg;

		@JsonProperty("urank")
		private int urank;

		@JsonProperty("verified_trade")
		private String verifiedTrade;

		@JsonProperty("weihao")
		private String weihao;

		@JsonProperty("green_mode")
		private int greenMode;

		@JsonProperty("mb_expire_time")
		private int mbExpireTime;

		@JsonProperty("verified_source_url")
		private String verifiedSourceUrl;

		@JsonProperty("video_mark")
		private int videoMark;

		@JsonProperty("live_status")
		private int liveStatus;

		@JsonProperty("special_follow")
		private boolean specialFollow;

		@JsonProperty("followers_count_str")
		private String followersCountStr;

		@JsonProperty("chaohua_ability")
		private int chaohuaAbility;

		@JsonProperty("like")
		private boolean like;

		@JsonProperty("verified_type")
		private int verifiedType;

		@JsonProperty("verified_type_ext")
		private int verifiedTypeExt;

		@JsonProperty("pagefriends_count")
		private int pagefriendsCount;

		@JsonProperty("name")
		private String name;

		@JsonProperty("cover_image_phone")
		private String coverImagePhone;

		@JsonProperty("idstr")
		private String idstr;

		@JsonProperty("description")
		private String description;

		@JsonProperty("remark")
		private String remark;

		@JsonProperty("ptype")
		private int ptype;

		@JsonProperty("verified_reason_url")
		private String verifiedReasonUrl;

		@JsonProperty("block_word")
		private int blockWord;

		@JsonProperty("verified_state")
		private int verifiedState;

		@JsonProperty("avatar_type")
		private int avatarType;

		@JsonProperty("avatar_hd")
		private String avatarHd;

		@JsonProperty("hongbaofei")
		private int hongbaofei;

		@JsonProperty("video_play_count")
		private int videoPlayCount;

		@JsonProperty("mbtype")
		private int mbtype;

		@JsonProperty("user_ability")
		private int userAbility;

		@JsonProperty("story_read_state")
		private int storyReadState;

		@JsonProperty("mbrank")
		private int mbrank;

		@JsonProperty("lang")
		private String lang;

		@JsonProperty("class")
		private int jsonMemberClass;

		@JsonProperty("allow_all_comment")
		private boolean allowAllComment;

		@JsonProperty("verified")
		private boolean verified;

		@JsonProperty("profile_image_url")
		private String profileImageUrl;

		@JsonProperty("pc_new")
		private int pcNew;

		@JsonProperty("paycolumn_ability")
		private int paycolumnAbility;

		@JsonProperty("brand_account")
		private int brandAccount;

		@JsonProperty("verified_contact_name")
		private String verifiedContactName;

		@JsonProperty("vclub_member")
		private int vclubMember;

		@JsonProperty("followers_count")
		private int followersCount;

		@JsonProperty("is_guardian")
		private int isGuardian;

		@JsonProperty("location")
		private String location;

		@JsonProperty("svip")
		private int svip;

		@JsonProperty("verified_reason_modified")
		private String verifiedReasonModified;

	}

	@Data
	public static class AnnotationsItem {

		@JsonProperty("mapi_request")
		private boolean mapiRequest;

		@JsonProperty("client_mblogid")
		private String clientMblogid;

		@JsonProperty("photo_sub_type")
		private String photoSubType;

	}

	@Data
	public static class CommentManageInfo {

		@JsonProperty("comment_permission_type")
		private int commentPermissionType;

		@JsonProperty("comment_sort_type")
		private int commentSortType;

		@JsonProperty("approval_comment_type")
		private int approvalCommentType;

	}

	@Data
	public static class Insecurity {

		@JsonProperty("sexual_content")
		private boolean sexualContent;

	}

	@Data
	public static class PicUrlsItem {

		@JsonProperty("thumbnail_pic")
		private String thumbnailPic;

	}

	@Data
	public static class Status {

		@JsonProperty("isLongText")
		private boolean isLongText;

		@JsonProperty("hot_weibo_tags")
		private List<Object> hotWeiboTags;

		@JsonProperty("in_reply_to_status_id")
		private String inReplyToStatusId;

		@JsonProperty("annotations")
		private List<AnnotationsItem> annotations;

		@JsonProperty("mblogtype")
		private int mblogtype;

		@JsonProperty("source")
		private String source;

		@JsonProperty("attitudes_count")
		private int attitudesCount;

		@JsonProperty("rid")
		private String rid;

		@JsonProperty("bmiddle_pic")
		private String bmiddlePic;

		@JsonProperty("positive_recom_flag")
		private int positiveRecomFlag;

		@JsonProperty("can_reprint")
		private boolean canReprint;

		@JsonProperty("is_show_bulletin")
		private int isShowBulletin;

		@JsonProperty("hide_flag")
		private int hideFlag;

		@JsonProperty("mlevel")
		private int mlevel;

		@JsonProperty("in_reply_to_user_id")
		private String inReplyToUserId;

		@JsonProperty("hasActionTypeCard")
		private int hasActionTypeCard;

		@JsonProperty("id")
		private long id;

		@JsonProperty("text")
		private String text;

		@JsonProperty("new_comment_style")
		private int newCommentStyle;

		@JsonProperty("mblog_vip_type")
		private int mblogVipType;

		@JsonProperty("content_auth")
		private int contentAuth;

		@JsonProperty("visible")
		private Visible visible;

		@JsonProperty("gif_ids")
		private String gifIds;

		@JsonProperty("source_type")
		private int sourceType;

		@JsonProperty("pic_urls")
		private List<PicUrlsItem> picUrls;

		@JsonProperty("biz_feature")
		private long bizFeature;

		@JsonProperty("comments_count")
		private int commentsCount;

		@JsonProperty("userType")
		private int userType;

		@JsonProperty("idstr")
		private String idstr;

		@JsonProperty("text_tag_tips")
		private List<Object> textTagTips;

		@JsonFormat(pattern = "E MMM dd HH:mm:ss '+0800' yyyy", locale = "en")
		@JsonProperty("created_at")
		private LocalDateTime createdAt;

		@JsonProperty("mid")
		private String mid;

		@JsonProperty("geo")
		private Object geo;

		@JsonProperty("darwin_tags")
		private List<Object> darwinTags;

		@JsonProperty("pending_approval_count")
		private int pendingApprovalCount;

		@JsonProperty("in_reply_to_screen_name")
		private String inReplyToScreenName;

		@JsonProperty("pic_num")
		private int picNum;

		@JsonProperty("is_paid")
		private boolean isPaid;

		@JsonProperty("reposts_count")
		private int repostsCount;

		@JsonProperty("reward_exhibition_type")
		private int rewardExhibitionType;

		@JsonProperty("favorited")
		private boolean favorited;

		@JsonProperty("reprint_cmt_count")
		private int reprintCmtCount;

		@JsonProperty("thumbnail_pic")
		private String thumbnailPic;

		@JsonProperty("original_pic")
		private String originalPic;

		@JsonProperty("can_edit")
		private boolean canEdit;

		@JsonProperty("textLength")
		private int textLength;

		@JsonProperty("truncated")
		private boolean truncated;

		@JsonProperty("source_allowclick")
		private int sourceAllowclick;

		@JsonProperty("show_additional_indication")
		private int showAdditionalIndication;

		@JsonProperty("comment_manage_info")
		private CommentManageInfo commentManageInfo;

		@JsonProperty("more_info_type")
		private int moreInfoType;

	}

	@Data
	public static class StatusTotalCounter {

		@JsonProperty("total_cnt")
		private int totalCnt;

		@JsonProperty("repost_cnt")
		private int repostCnt;

		@JsonProperty("comment_like_cnt")
		private int commentLikeCnt;

		@JsonProperty("like_cnt")
		private int likeCnt;

		@JsonProperty("comment_cnt")
		private int commentCnt;

	}

	@Data
	public static class VideoTotalCounter {

		@JsonProperty("play_cnt")
		private int playCnt;

	}

	@Data
	public static class Visible {

		@JsonProperty("list_id")
		private int listId;

		@JsonProperty("type")
		private int type;

	}

}
