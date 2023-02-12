package houzz.mapper;

import org.springframework.stereotype.Repository;

@Repository(value = "houzz.mapper.EstateMapper")
public interface EstateMapper {
	public String estateNumGenerate();
}
