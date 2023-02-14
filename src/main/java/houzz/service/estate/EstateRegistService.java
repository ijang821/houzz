package houzz.service.estate;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import houzz.command.EstateCommand;
import houzz.domain.AuthInfoDTO;
import houzz.domain.EstateDTO;
import houzz.domain.OptionsDTO;
import houzz.mapper.EstateMapper;
import jakarta.servlet.http.HttpSession;

@Service
public class EstateRegistService {
	@Autowired
	EstateMapper estateMapper;
	public void execute(EstateCommand estateCommand, HttpSession session) {
		EstateDTO estDTO = new EstateDTO();
		estDTO.setEstateAddr(estateCommand.getEstateAddr());
		estDTO.setEstateExplain(estateCommand.getEstateExplain());
		estDTO.setEstateInfo(estateCommand.getEstateInfo());
		estDTO.setEstateName(estateCommand.getEstateName());
		estDTO.setEstateNum(estateCommand.getEstateNum());
		estDTO.setEstatePrice(estateCommand.getEstatePrice());
		
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
		
		
		/* 파일 저장 */
		String fileDir = "/view/estate/upload";
		String filePath = session.getServletContext().getRealPath(fileDir);
		
								
		if (!estateCommand.getEstatePic()[0].getOriginalFilename().isEmpty()) {
			String originalTotal = "";	
			String storeTotal = "";

			for (MultipartFile mf : estateCommand.getEstatePic()) {
				//// 파일명 만들기 ////
				String originalFile = mf.getOriginalFilename();
				// 확장자 가지고 오기
				String extension = originalFile.substring(originalFile.lastIndexOf("."));
				String storeName = UUID.randomUUID().toString().replace("-", "");
				String storeFileName = storeName + extension;
				/// 파일명 만들기 ////
				File file = new File(filePath + "/" + storeFileName);
				try {
					mf.transferTo(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				originalTotal += originalFile + "-";
				storeTotal += storeFileName + "-";
			}
			estDTO.setEstatePic(storeTotal);
			estDTO.setOriginalEstatePic(originalTotal);
		}
		estateMapper.estateInsert(estDTO);
		
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
		opDTO.setEstateNum(estateCommand.getEstateNum());
		 
		estateMapper.estateOptions(opDTO);
	}
	
	
	public void createPdf(EstateCommand estateCommand, String fileName) {
		String result = ""; 
		try {
			Document document = new Document(); // pdf문서를 처리하는 객체
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open(); 
			BaseFont baseFont = BaseFont.createFont("src/main/resources/static/font/NanumBarunGothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			Font font = new Font(baseFont, 12);
			
			PdfPTable table = new PdfPTable(2); // 2개의 셀을 가진 테이블 객체를 생성 (pdf파일에 나타날 테이블)
			Chunk chunk = new Chunk("매물 정보", font); // 타이틀 객체를 생성 (타이틀의 이름을 회원정보로 하고 위에 있는 font를 사용)
			
			Paragraph ph = new Paragraph(chunk);
            ph.setAlignment(Element.ALIGN_CENTER);
            document.add(ph); // 문단을 만들어서 가운데 정렬 (타이틀의 이름을 가운데 정렬한다는 뜻)
 
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE); // 줄바꿈 (왜냐하면 타이틀에서 두줄을 내린후에 셀(테이블)이 나오기 때문)
            
            PdfPCell cell = new PdfPCell(new Phrase("매물 정보", font)); // 셀의 이름과 폰트를 지정해서 셀을 생성한다.
            cell.setHorizontalAlignment(Element.ALIGN_CENTER); // 셀의 정렬방식을 지정한다. (가운데정렬)
            cell.setColspan(2);
            
            /// 회원 번호
            PdfPCell cell1 = new PdfPCell(new Phrase("매물 번호", font)); // 셀의 이름과 폰트를 지정해서 셀을 생성한다.
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER); // 셀의 정렬방식을 지정한다. (가운데정렬)
 
            PdfPCell cell2 = new PdfPCell(new Phrase(estateCommand.getEstateNum(), font));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            
            // 회원아이디
            PdfPCell cell21 = new PdfPCell(new Phrase("매물명", font));
            cell21.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell22 = new PdfPCell(new Phrase(estateCommand.getEstateName(), font));
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            // 회원 이름
            PdfPCell cell31 = new PdfPCell(new Phrase("매물 가격", font));
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell32 = new PdfPCell(new Phrase(String.valueOf(estateCommand.getEstatePrice()), font));
            cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            // 회원 주소
            PdfPCell cell41 = new PdfPCell(new Phrase("주소", font));
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
 
            PdfPCell cell42 = new PdfPCell(new Phrase(estateCommand.getEstateAddr(), font));
            cell42.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            table.addCell(cell); // 그리고 테이블에 위에서 생성시킨 셀을 넣는다.
            table.addCell(cell1); 
            table.addCell(cell2);
            table.addCell(cell21);
            table.addCell(cell22);
            table.addCell(cell31);
            table.addCell(cell32);
            table.addCell(cell41);
            table.addCell(cell42);
           
            document.add(table); // 웹접근 객체에 table를 저장한다.
            document.close(); // 저장이 끝났으면 document객체를 닫는다.
            result = "pdf 파일이 생성되었습니다.";
    
		}catch(Exception e) {
			 e.printStackTrace();
	         result = "pdf 파일 생성 실패...";
		}
		System.out.println(result);
	}
	
}
