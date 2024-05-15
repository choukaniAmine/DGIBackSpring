package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.DetailImpotDto;
import com.example.demo.dtos.PeriodeDto;
import com.example.demo.dtos.TypeImpotDto;
import com.example.demo.entities.DetailImpot;
import com.example.demo.entities.Periodicite;
import com.example.demo.entities.TypeImpot;
import com.example.demo.repository.DetailImpotRepositpry;
import com.example.demo.repository.TypeImpotRepository;

@Service
public class DetailImpotService {
@Autowired
private DetailImpotRepositpry detailImpot;
@Autowired
private TypeImpotRepository typeImpotRepository;
public DetailImpotDto saveDetailImpot(DetailImpotDto dt) {
	 TypeImpot impot = typeImpotRepository.findByLibelle(dt.getTypeImpot().getLibelle())
             .orElseThrow(() -> new IllegalArgumentException("Invalid impot"));
	 DetailImpot detail=new DetailImpot();
	 detail.setLibelle(dt.getLibelle());
	 detail.setNatureRubrique(dt.getNatureRubrique());
	 detail.setObligatoire(dt.isObligatoire());
	 detail.setOrdre(dt.getOrdre());
	 detail.setTypeDetail(dt.getTypeDetail());
	 detail.setTypeImpot(impot);
	 DetailImpot detailcree=detailImpot.save(detail);
	 DetailImpotDto detaildto=new DetailImpotDto();
	
	 detaildto.setLibelle(detailcree.getLibelle());
	 detaildto.setNatureRubrique(detailcree.getNatureRubrique());
	 detaildto.setObligatoire(detailcree.isObligatoire());
	 detaildto.setTypeDetail(detailcree.getTypeDetail());
	 TypeImpotDto impotdto=new TypeImpotDto();
	 impotdto.setLibelle(detailcree.getTypeImpot().getLibelle());
	 PeriodeDto pd=new PeriodeDto();
	 pd.setIdPeriodicite(detailcree.getTypeImpot().getPeriodicite().getIdPeriodicite());
	 pd.setPeriode(detailcree.getTypeImpot().getPeriodicite().getPeriode());
	impotdto.setPeriodicite(pd);
	detaildto.setTypeImpot(impotdto);
	return detaildto;
}
public List<DetailImpot> findByTypeImpot(String libelle){
	Optional<TypeImpot> type=typeImpotRepository.findByLibelle(libelle);
	if(type.get()!=null) {
		List<DetailImpot> list=detailImpot.findByTypeImpot(type.get());
		return list;
	}else return null;
}
}
