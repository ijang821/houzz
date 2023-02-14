package houzz.service.estate;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import houzz.domain.EstateDTO;
import houzz.mapper.EstateMapper;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class EstateDeleteService {
	@Autowired
	EstateMapper estateMapper;
	public void execute(String estateNum, HttpServletRequest request) {
		EstateDTO estDTO = estateMapper.selectOne(estateNum);
		Integer i = estateMapper.estateDelete(estateNum);
		if(i > 0 ) {
			String fileDir = "/view/estate/upload";
			String filePath=request.getServletContext().getRealPath(fileDir);
			if(estDTO.getEstatePic() != null){
				String [] fileNames =  estDTO.getEstatePic().split("-");
				for(String f : fileNames) {
					File file = new File(filePath + "/" + f);
					if(file.exists())file.delete();
				}
			}
		}
	}
}
