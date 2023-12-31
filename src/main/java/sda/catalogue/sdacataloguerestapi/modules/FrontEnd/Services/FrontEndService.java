package sda.catalogue.sdacataloguerestapi.modules.FrontEnd.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import sda.catalogue.sdacataloguerestapi.core.CustomResponse.PaginateResponse;
import sda.catalogue.sdacataloguerestapi.core.Exception.CustomRequestException;
import sda.catalogue.sdacataloguerestapi.modules.FrontEnd.Dto.FrontEndDTO;
import sda.catalogue.sdacataloguerestapi.modules.FrontEnd.Entities.FrontEndEntity;
import sda.catalogue.sdacataloguerestapi.modules.FrontEnd.Repositories.FrontEndRepository;

import java.util.List;
import java.util.UUID;

@Service
public class FrontEndService {
    @Autowired
    private FrontEndRepository frontEndRepository;

    public PaginateResponse<List<FrontEndEntity>> searchFrontEnd(String searchTerm, long page, long size) {
        Pageable pageable = PageRequest.of((int) (page - 1), (int) size);
        List<FrontEndEntity> result = frontEndRepository.findBySearchTerm(searchTerm, pageable);
        long total = frontEndRepository.countBySearchTerm(searchTerm);
        PaginateResponse.Page pageInfo = new PaginateResponse.Page(size, total, page);
        return new PaginateResponse<>(result, pageInfo);
    }

    public FrontEndEntity getFrontEndByUuid(UUID uuid) {
        FrontEndEntity result = frontEndRepository.findByUuid(uuid);
        if (result == null) {
            throw new CustomRequestException("UUID" + uuid + " not found", HttpStatus.NOT_FOUND);
        }
        return result;
    }

    public FrontEndEntity createFrontEnd(FrontEndDTO request) {
        FrontEndEntity data = new FrontEndEntity();
        data.setFrontEnd(request.getFrontEnd());
        return frontEndRepository.save(data);
    }


    @Transactional
    public FrontEndEntity updateFrontEnd(UUID uuid, FrontEndDTO request) {
        int result = frontEndRepository.findByUuidAndUpdate(uuid, request.getFrontEnd());
        FrontEndEntity findData = frontEndRepository.findByUuid(uuid);
        if (result > 0) {
            return findData;
        } else {
            throw new CustomRequestException("UUID " + uuid + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public FrontEndEntity deleteFrontEnd(UUID uuid) {
        FrontEndEntity findData = frontEndRepository.findByUuid(uuid);
        int result = frontEndRepository.findByUuidAndDelete(uuid);
        if (result > 0) {
            return findData;
        } else {
            throw new CustomRequestException("UUID " + uuid + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
