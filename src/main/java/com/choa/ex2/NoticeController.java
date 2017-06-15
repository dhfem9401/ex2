package com.choa.ex2;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choa.notice.NoticeDTO;
import com.choa.notice.NoticeService;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {

	@Inject//type
	private NoticeService noticeService;
	
	//list
	@RequestMapping(value="noticeList", method=RequestMethod.GET)
	public void noticeList(Model model, @RequestParam(defaultValue="1") Integer curPage) throws Exception{
		List<NoticeDTO> ar = noticeService.noticeList(curPage);
		model.addAttribute("list", ar);
	}
	
	//View
	@RequestMapping(value="noticeView", method=RequestMethod.GET)
	public void noticeView(Integer num, Model model) throws Exception{
		NoticeDTO noticeDTO=noticeService.noticeView(num);
		model.addAttribute("dto", noticeDTO);
	}
	
	//writeForm
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public void noticeWrite(){}
		
	
	//write 
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public String noticeWrite(NoticeDTO noticeDTO, RedirectAttributes rd) throws Exception{
		int result=noticeService.noticeWrite(noticeDTO);
		String message = "FAIL";
		if(result>0){
			message="SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList?curPage=2";
	}
	
	//update
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String noticeUpdate(NoticeDTO noticeDTO, RedirectAttributes rd)throws Exception{
		int result=noticeService.noticeWrite(noticeDTO);
		String message = "FAIL";
		if(result>0){
			message="SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList?curPage=1";
	}
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.POST)
	public void noticeUpdate2(NoticeDTO noticeDTO){
		
	}
	//delete
	@RequestMapping(value="noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(Integer num, RedirectAttributes rd)throws Exception{
		int result = noticeService.noticeDelete(num);
		String message = "FAIL";
		if(result>0){
			message="SUCCESS";
		}
		rd.addFlashAttribute("message", message);
		return "redirect:noticeList";
		
	}
	
	
	
}
