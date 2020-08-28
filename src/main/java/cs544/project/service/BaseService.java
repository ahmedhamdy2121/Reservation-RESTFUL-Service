package cs544.project.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cs544.project.repository.BaseRepository;
import cs544.project.repository.search.SpecificationBuilder;
import cs544.project.service.mapper.BaseMapper;

// DomainModel, Request,Response
public abstract class BaseService<D, R, P, I> {

    @Autowired
    protected BaseRepository<D, I> repository;

    @Autowired
    protected BaseMapper<D, R, P> mapper;

    public P findById(I id) throws Exception {
        D entity = (D) repository.findById(id).orElseThrow(Exception::new);
        return mapper.domainToResponse(entity);
    }

    public List<P> findAll() {
        return convertEntityListToResponseList(repository.findAll());
    }

    public Long findAllCount() {
        return repository.count();
    }

    public Page<P> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::domainToResponse);
    }

    public Long searchCount(String query) {
        return repository.count(new SpecificationBuilder<D>(query).build());
    }

    public List<P> search(String query) {
        return convertEntityListToResponseList(repository
                .findAll(new SpecificationBuilder<D>(query).build()));
    }

    public Page<P> search(String query, Pageable pageable) {
        return repository
                .findAll(new SpecificationBuilder<D>(query).build(), pageable)
                .map(mapper::domainToResponse);
    }

    public List<P> convertEntityListToResponseList(List<D> entityList) {
        if (null == entityList) {
            return null;
        } else {
            return entityList.stream().map(mapper::domainToResponse)
                    .collect(Collectors.toList());
        }
    }

    public Page<P> convertEntityPageToResponsePage(Page<D> entityPage) {
        if (null == entityPage) {
            return null;
        } else {
            return entityPage.map(mapper::domainToResponse);
        }
    }

    public void save(R request) {
        D entity = mapper.requestToDomain(request);
        repository.save(entity);
    }

    public void delete(I id) throws Exception {
        D entity = (D) repository.findById(id).orElseThrow(Exception::new);
        repository.delete(entity);
    }

}
