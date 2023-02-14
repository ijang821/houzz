package houzz.service.estate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import houzz.command.FileInfo;
import jakarta.servlet.http.HttpSession;

@Service
public class FileDelService {
	public void execute(FileInfo fileInfo, HttpSession session, Model model) {
		Integer num = 0;
		Boolean newFile = true; // session을 확인하기 위한 변수
		/// list가 null이면 
		List<FileInfo> list = (List<FileInfo>)session.getAttribute("fileList");
		if(list == null) { 
			list = new ArrayList<FileInfo>();
		}
		// 
		for(int i = 0; i < list.size() ; i++) {
			if(list.get(i).getOrgFile().equals(fileInfo.getOrgFile())) {
				list.remove(i);
				newFile = false;
				num =0;
			}
		}
		if(newFile) {
			list.add(fileInfo);
			num = 1;
		}
		System.out.println(list.size());
		session.setAttribute("fileList", list);
		model.addAttribute("val", num);
	}
}
