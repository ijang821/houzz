package houzz.service.estate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import houzz.command.EstateCommand;
import houzz.command.FileInfo;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EstateDTO;
import houzz.domain.OptionsDTO;
import houzz.mapper.EstateMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EstateModifyService {
	@Autowired
	EstateMapper estateMapper;
	public void execute(EstateCommand estateCommand, HttpSession session) {
		EstateDTO estDTO = new EstateDTO();
		estDTO.setEstateInfo(estateCommand.getEstateInfo());
		estDTO.setEstateExplain(estateCommand.getEstateExplain());
		estDTO.setEstateAddr(estateCommand.getEstateAddr());
		estDTO.setEstateName(estateCommand.getEstateName());
		estDTO.setEstateNum(estateCommand.getEstateNum());
		estDTO.setEstatePrice(estateCommand.getEstatePrice());
		
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		
		List<FileInfo> list = (List<FileInfo>)session.getAttribute("fileList");
		
		EstateDTO lib = estateMapper.selectOne(estateCommand.getEstateNum());
		
		String fileDir = "/view/goods/upload";
		String filePath=session.getServletContext().getRealPath(fileDir);
		
		String [] images = lib.getEstatePic().split("-");
		String [] original = lib.getOriginalEstatePic().split("-");
		List<String> estatePic = new ArrayList<String>();
		List<String> originalEstatePic = new ArrayList<String>();
		for(String str : images) { 
			estatePic.add(str);	
		}
		for(String str: original) { 
			originalEstatePic.add(str); 
		}
		
		if(list != null) {
			for(FileInfo  fi : list) {
				for(String str : estatePic) {
					if(str.equals(fi.getStrFile())) {
						estatePic.remove(fi.getStrFile());
						originalEstatePic.remove(fi.getOrgFile());
						break;
					}
				}
			}
		}
		String storeTotal = "";
		String originalTotal = "";
		if(!estateCommand.getEstatePic()[0].getOriginalFilename().isEmpty()) {
			for(MultipartFile mf : estateCommand.getEstatePic() ) {
				String originalFile = mf.getOriginalFilename();
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				String storeName = UUID.randomUUID().toString().replace("-", "");
				String storeFileName=storeName + extension;
				storeTotal += storeFileName +"-";
				originalTotal += originalFile + "-";
				File file = new File(filePath + "/" + storeFileName);
				try {
					mf.transferTo(file); // 파일을 저장
				}catch(Exception e) {e.printStackTrace();}
			}
		}
		// 수정된 정보와 추가된 파일정보를 합치기
		for(String str : estatePic) {
			storeTotal += str  +"-";
		}
		for(String str : originalEstatePic) {
			originalTotal += str  +"-";
		}
		
		estDTO.setOriginalEstatePic(originalTotal);
		estDTO.setEstatePic(storeTotal);
			
		int i = estateMapper.estateUpdate(estDTO);
		
		// session에 있는 파일 삭제
		if(i > 0) {
			if(list != null) {
				for(FileInfo fi : list ) {
					File file = new File(filePath + "/" + fi.getStrFile());
					if(file.exists()) file.delete();
				}
			}
			
		}
		
		OptionsDTO opDTO = new OptionsDTO();
		
		String Y = "Y";
		String N = "N";
		if(estateCommand.isAirConditioner() == true){
			opDTO.setAirConditioner(Y);
		}else {
			opDTO.setAirConditioner(N);
		}
		if(estateCommand.isBed() == true){
			opDTO.setBed(Y);
		}else {
			opDTO.setBed(N);
		}
		if(estateCommand.isBidet() == true){
			opDTO.setBidet(Y);
		}else {
			opDTO.setBidet(N);
		}
		if(estateCommand.isCloset() == true){
			opDTO.setCloset(Y);
		}else {
			opDTO.setCloset(N);
		}
		if(estateCommand.isDesk() == true){
			opDTO.setDesk(Y);
		}else {
			opDTO.setDesk(N);
		}
		if(estateCommand.isDoorLock() == true){
			opDTO.setDoorLock(Y);
		}else {
			opDTO.setDoorLock(N);
		}
		if(estateCommand.isGasStove() == true){
			opDTO.setGasStove(Y);
		}else {
			opDTO.setGasStove(N);
		}
		if(estateCommand.isInduction() == true){
			opDTO.setInduction(Y);
		}else {
			opDTO.setInduction(N);
		}
		if(estateCommand.isMicrowave() == true){
			opDTO.setMicrowave(Y);
		}else {
			opDTO.setMicrowave(N);
		}
		if(estateCommand.isRefrigerator() == true){
			opDTO.setRefrigerator(Y);
		}else {
			opDTO.setRefrigerator(N);
		}
		if(estateCommand.isShoeShelf() == true){
			opDTO.setShoeShelf(Y);
		}else {
			opDTO.setShoeShelf(N);
		}
		if(estateCommand.isTv() == true){
			opDTO.setTv(Y);
		}else {
			opDTO.setTv(N);
		}
		if(estateCommand.isWasher() == true){
			opDTO.setWasher(Y);
		}else {
			opDTO.setWasher(N);
		}		
		opDTO.setEstNum(estateCommand.getEstateNum());
		 
		estateMapper.updateOptions(opDTO);
	}
}
