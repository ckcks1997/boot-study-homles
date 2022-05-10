package com.example.bootstudyhomles.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.bootstudyhomles.model.Notice;
import com.example.bootstudyhomles.model.Report;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.bootstudyhomles.model.GroupMember;
import com.example.bootstudyhomles.model.ReputationEstimate;
import com.example.bootstudyhomles.model.StudyMember;
import com.example.bootstudyhomles.model.StudyMenu;
import com.example.bootstudyhomles.service.CommunityBoardDao;
import com.example.bootstudyhomles.service.GroupMemberDao;
import com.example.bootstudyhomles.service.KakaoService;
import com.example.bootstudyhomles.service.NoticeDao;
import com.example.bootstudyhomles.service.ReportDao;
import com.example.bootstudyhomles.service.ReputationEstimateDao;
import com.example.bootstudyhomles.service.StudyMemberDao;
import com.example.bootstudyhomles.service.StudyMenuDao;


@Controller  
@RequestMapping("/studymember/") 
public class StudyMemberController {

	StudyMemberDao md;
	StudyMenuDao mud;
	CommunityBoardDao cbd;
	GroupMemberDao gmd;
	NoticeDao nd;
	ReportDao rd;
	ReputationEstimateDao red;
	
    KakaoService kakaoService;
    PasswordEncoder passwordEncoder;

	public StudyMemberController(StudyMemberDao md, StudyMenuDao mud, CommunityBoardDao cbd, GroupMemberDao gmd,
			NoticeDao nd, ReportDao rd, ReputationEstimateDao red, KakaoService kakaoService,
			PasswordEncoder passwordEncoder) {
		this.md = md;
		this.mud = mud;
		this.cbd = cbd;
		this.gmd = gmd;
		this.nd = nd;
		this.rd = rd;
		this.red = red;
		this.kakaoService = kakaoService;
		this.passwordEncoder = passwordEncoder;
	}

	
	HttpServletRequest request;
	HttpSession session;
	
	@ModelAttribute
	void init(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}
	

  /*
   * 알림 페이지
   * */
  @RequestMapping("notice")
  public String memberNotice(Model model) {

	  String id = (String) session.getAttribute("memberNickname");
      List<Notice> noticeList = nd.noticeGet(id); //알림 리스트 가져옴
      nd.noticeRead(id);
      
      model.addAttribute("noticeList", noticeList);
      session.setAttribute("noticeCount", 0);
      return "/view/member/memberNotice";      

  }
  
  /*
   * 알림 상세
   * */
  @RequestMapping("noticeInfo")
  public String noticeInfo(Model model) {
 
	  String id = (String) session.getAttribute("memberNickname"); 
      int noticeNum = Integer.parseInt(request.getParameter("noticeNum"));
      
      //noticeNum에 해당하는 notice정보 가져오기
      Notice n = nd.noticeGetByNoticeNum(noticeNum);
      if(n.getNickname_to().equals(id)) { //알림의 수신자와 현재 닉네임이 같으면
      
    	  if(n.getInfo().contains("study")) {//Info이 없으면 Info2 이용해서 title 채워보내기
    		   
    		  StudyMenu menu = mud.menuBoardOne(Integer.parseInt(n.getInfo2()));
    		  String title = menu.getTitle();  
    		  model.addAttribute("title", title);
         
    	  }  else if(n.getInfo().contains("report")) { //Info가 있으면
    		  //신고사유 가져오기
    		  int board_num = Integer.parseInt(n.getInfo2());
    		  List<String> reportReason = rd.reportReason(board_num);
    		  model.addAttribute("reportReason", reportReason);
    		  
    		  //report테이블에서 원 글 정보가져오기 
    		  Report report = rd.reportOne(board_num);
    		  model.addAttribute("report", report);

    	  }
      // notice 보내기
    	  model.addAttribute("notice", n);  
    	  return "/view/member/memberNoticeInfo";
      }      
 
    model.addAttribute("msg", "오류 발생");  
    return "redirect:/board/main";
  }
  
  @ResponseBody
  @RequestMapping(value="noticeDelete", produces = MediaType.TEXT_PLAIN_VALUE)
  public String noticeDelete(@RequestBody Map<String, String> req) { 
	  Integer noticeNum = Integer.parseInt(req.get("noticeNum")); 
	  int result= nd.noticeDelete(noticeNum);
	  return result == 1 ? "1" : "0"; //알림 삭제 성공시 1
  }
  
  
  /*
   * 알림-그룹초대 수락
   * */
  @RequestMapping("groupAccept")
  public String groupAccept(RedirectAttributes redirect, Integer notice_num, Integer accept) {

	  String id = (String) session.getAttribute("memberNickname");
	
	  String msg = "오류발생";
	  String url = "/board/main"; 
	  if(notice_num != null && accept != null) {
	      int noticeNum = notice_num; //알림번호 가져옴
	      Notice n = nd.noticeGetByNoticeNum(noticeNum); //알림정보 조회
	      StudyMenu menu = mud.menuBoardOne(Integer.parseInt(n.getInfo2())); //알림정보에 있는 스터디 보드번호로 보드조회
	 
	      if(n.getNickname_to().equals(id)) { //세션과 알림 받은사람 비교, 본인확인
	        //group에 초대하는 과정
	        GroupMember gm = new GroupMember();
	        gm.setBoardnum(menu.getBoard_num());
	        gm.setNickname(n.getNickname_from()); 
	        
	       if(accept == 1) { 
	        gmd.groupInsert(gm, 0);
	        nd.noticeWriteAll(n.getNickname_from(), n.getNickname_to(), "text: 스터디 초대 알림","\'"+ menu.getTitle()+"\' 스터디에 초대되었습니다.");
	        nd.noticeDelete(noticeNum);
	       } else  if(accept == 0) { 
	           nd.noticeWriteAll(n.getNickname_from(), n.getNickname_to(), "text: 스터디 거부 알림","\'"+ menu.getTitle()+"\' 스터디 초대가 거부되었습니다.");
	           nd.noticeDelete(noticeNum);
	       }
	        msg = "등록되었습니다";
	        url = "/studymember/notice";   
	      }
	  }
    redirect.addFlashAttribute("msg", msg);  
    return "redirect:"+url;
  }
  
  /*
   * 로그인 페이지
   * */
  @RequestMapping("loginForm")
  public String memberloginForm() {
    return "view/member/login";
  }

  /*
   * 로그인 과정
   * 
   * 로그인 성공시에는 memberID(이메일)와 memberNickname(닉네임, 이름x!!),memberPicture 3가지 세션이 생성됩니다.
   * */
  
  @PostMapping("loginPro")
  public String memberloginPro(Model model) {

    String id = request.getParameter("id"); // email
    String pass = request.getParameter("password"); 
    
    StudyMember mem = md.studyMemberOne(id);
    
    String msg = "아이디를 확인하세요";
    String url = "/studymember/loginForm";
    if (mem != null) {
      if (pass.equals(mem.getPassword()) || passwordEncoder.matches(pass, mem.getPassword())) {
        request.getSession().setAttribute("memberID", mem.getEmail());
        request.getSession().setAttribute("memberNickname", mem.getNickname());
        request.getSession().setAttribute("memberPicture", mem.getPicture());
        msg = "";
        url = "/board/main";
      } else {
        System.out.println(mem);
        msg = "비밀번호 확인하세요";
      }
    } 
    model.addAttribute("msg", msg);  
    return "redirect:"+url;
  }
  
  /*
   * 카카오 로그인
   * */
  @RequestMapping("kakaologin")
  public String kakaoLogin(@RequestParam(value = "code", required = false) String code, Model model) {
      String access_Token = kakaoService.getAccessToken(code);
      HashMap<String, Object> userInfo = kakaoService.getUserInfo(access_Token);
      
      StudyMember mem = md.studyMemberOne((String)userInfo.get("email"));
      if(mem == null) {
    	  model.addAttribute("kakaoemail", userInfo.get("email"));
    	  model.addAttribute("kakaonick", userInfo.get("nickname"));
    	  return "view/member/join";
      }
      request.getSession().setAttribute("memberID", mem.getEmail());
      request.getSession().setAttribute("memberNickname", mem.getNickname());
      request.getSession().setAttribute("memberPicture", mem.getPicture());
    return "redirect:/board/main";
  }
  
  /*
   * 로그아웃-세션 삭제
   * */
  @RequestMapping("logout")
  public String logout() {
	  
    HttpSession session = request.getSession();
    String login = (String) session.getAttribute("memberNickname");
    session.invalidate();
    request.setAttribute("msg", "");
    String url = "/board/main";
    
    return "redirect:"+url;
  } 
  
    /*회원가입*/
  
  /*
   * 회원가입 페이지
   * */
  @RequestMapping("join")
  public String memberJoin() {

    if(request.getSession().getAttribute("memerNickname") == null) {      
      return "/view/member/join";
    }
     
    String url ="/board/main"; 

    return "redirect:"+url;
  }

  /*
   * 회원가입 진행
   * 
   * */
  @RequestMapping("joinPro")
  public String memberJoinPro(StudyMember m, Model model) {
	String encPass= passwordEncoder.encode(m.getPassword());
	m.setPassword(encPass);
    int result = md.insertStudyMember(m);
    
    String msg = "가입 실패";
    String url = "/studymember/loginForm";
    
    if(result == 1) msg="가입 성공";
    
    model.addAttribute("msg", msg); 
    return "redirect:"+url;
  }
  /*
   * id중복체크
   * */
  
  @ResponseBody
  @RequestMapping("idexist")
  public String idExist(@RequestBody String id) {
  
    System.out.println("id="+id); 
    int mem = md.studyMemberIdExist(id); 
      
    return Integer.toString(mem);
  }
  
  /*
   * 닉네임 중복확인
   * */
  @ResponseBody //Ajax 통신
  @RequestMapping(value="nicknameExist") 
  public String nicknameExist(@RequestBody String nickname) {  
    int mem = md.studyMemberNicknameExist(nickname); 
    System.out.println("result="+mem); 
      
    return Integer.toString(mem);
  }
  
  /*
   * 회원가입 내 사진등록 창
   * */
  @RequestMapping("pictureForm")
  public String pictureForm() {
    return "/single/pictureForm";
  }
  
  /*
   * 회원가입 내 사진등록 진행
   * */
  @RequestMapping("picturePro")
  public String picturePro(@RequestParam("picture") MultipartFile file, Model model) {
    
    String path = request.getServletContext().getRealPath("/")+"imgupload/";
    System.out.println(path);
    File folder = new File(path);
    if(!folder.exists()) {
    	folder.mkdir();
    }
    String filename = file.getOriginalFilename();

    if(!file.isEmpty()) {
    	File file2 = new File(path, file.getOriginalFilename());
    	try {
    		file.transferTo(file2); 
		} catch (IllegalStateException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
    }

    model.addAttribute("filename", filename);
    return "/single/picturePro";
  }
  
  
  /*
   * 마이페이지
   * */
  
  @RequestMapping("mypage")
  public String mypage(Model model) {
      String memberID = (String) session.getAttribute("memberID"); 
      StudyMember mem = md.studyMemberOne(memberID);
      model.addAttribute("memberInfo", mem);
    //유저 평판
      List<ReputationEstimate> repList = red.getReputation(mem.getNickname());
      model.addAttribute("repList", repList);

      return "/view/member/mypage"; 
  }
  
    /*내  프로필 정보*/
  
  /*
   * 내 프로필 정보
   * */
  
  @RequestMapping("myprofile")
  public String myprofile(Model model) {
      String memberID = (String) session.getAttribute("memberID");   
      StudyMember mem = md.studyMemberOne(memberID);
      model.addAttribute("memberInfo", mem);
      
      return "/view/member/myprofile";
  }
  
  /*
   * 사진변경
   * */
  @RequestMapping("pictureChange")
  public String pictureChange(@RequestParam("picture") MultipartFile file) {
    
    String path = request.getServletContext().getRealPath("/")+"imgupload/";
    File folder = new File(path);
    if(!folder.exists()) {
    	folder.mkdir();
    }
    String filename = file.getOriginalFilename();

    if(!file.isEmpty()) {
    	File file2 = new File(path, file.getOriginalFilename());
    	try {
    		file.transferTo(file2); 
		} catch (IllegalStateException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
    }
    
    md.changePic((String)session.getAttribute("memberNickname"), filename);
    session.setAttribute("memberPicture", filename);
    
    return "redirect:/studymember/mypage";
  }
  
  /*
   * 내 프로필 정보-자기소개 수정칸
   * */
  @PostMapping("myprofileEdit1")
  public String myprofileEdit1(Model model, RedirectAttributes redirect) { 
	  String msg="오류 발생"; 
	  String url= "redirect:/studymember/myprofile";
      String s_id = (String)session.getAttribute("memberID");
      String profile_intro = (String) request.getParameter("profile_intro");
 
      int result = md.studyMemberIntroUpdate(s_id, profile_intro);
      if(result == 1) {
        msg="수정되었습니다"; 
      }
     //메세지 값을 post방식으로 보냅니다.
    redirect.addFlashAttribute("msg", msg); 
    return url;
  }
  
  
  /*
   * 비밀번호 변경
   * */
  @RequestMapping("passwordChange")
  public String passwordChange(Model model) {
      return "/view/member/passwordChange"; 
  }
  
  /*
   * 비밀번호 변경 진행
   * */
  @PostMapping("passwordChangePro")
  public String passwordChangePro(
		  @RequestParam("password") String password,
		  @RequestParam("newpassword") String newpassword, 
		  RedirectAttributes redirect ) {
 
    String msg="오류 발생";
    String url="redirect:/studymember/myprofile";
    
    if(!password.isEmpty()) {
    	msg="비밀번호가 다릅니다.";
      String s_id = (String)request.getSession().getAttribute("memberID");
      StudyMember sm = md.studyMemberOne(s_id);
      if(sm.getPassword().equals(password)){
	      int result = md.changePassword(s_id, newpassword); 
	      msg="비밀번호가 변경 되었습니다.";
      }
      url="redirect:/studymember/myprofile";
    }  
    redirect.addFlashAttribute("msg", msg); 
    return url;

  }
  
  /*
   * 회원탈퇴
   * */
  @RequestMapping("goodbye")
  public String goodBye(Model model) { 

      String memberID = (String) session.getAttribute("memberID");
      StudyMember mem = md.studyMemberOne(memberID);
      model.addAttribute("memberInfo", mem);
       
      return "/view/member/goodbye";
  }
  
  /*
   * 회원탈퇴 진행
   * */
  @PostMapping("goodbyePro")
  public String goodbyePro(RedirectAttributes redirect ) {
	  
	  String msg="알 수 없는 오류";
      String url="redirect:/board/main";

      String memberID = (String) session.getAttribute("memberID");
      String pw = request.getParameter("password");
       
      StudyMember mem = md.studyMemberOne(memberID);
      
      if(passwordEncoder.matches(pw, mem.getPassword()) || pw.equals(mem.getPassword())) {
        int deleted = md.studyMemberDelete(memberID);
        
        if(deleted == 1) {
          session.invalidate();
          msg="회원탈퇴가 완료되었습니다.";
        } else {
          msg="알 수 없는 오류";
        }
      }else {
        msg="비밀번호가 다릅니다";
        url="redirect:/studymember/goodbye";
      }
    redirect.addFlashAttribute("msg", msg); 
    return url; 
  }
 
  /*
   * 마이페이지
   * */
  @RequestMapping("userinfo")
  public String userinfo(Model model) {
    //유저정보
      String usernick = request.getParameter("usernick");
      StudyMember mem = md.studyMemberOneByNick(usernick);
      model.addAttribute("memberInfo", mem);
      //유저 평판
      List<ReputationEstimate> repList = red.getReputation(usernick);
      model.addAttribute("repList", repList);
      
    return "/view/member/userinfo";
  }
  
}


