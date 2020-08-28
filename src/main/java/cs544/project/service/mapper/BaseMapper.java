package cs544.project.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseMapper<D, R, P> {

    private final Class<D> domainClass;
    @SuppressWarnings("unused")
    private final Class<R> requestClass;
    private final Class<P> responseClass;

    @Autowired
    protected ModelMapper modelMapper;

    public BaseMapper(Class<D> domainClass, Class<R> requestClass,
                      Class<P> responseClass) {
        this.domainClass = domainClass;
        this.requestClass = requestClass;
        this.responseClass = responseClass;
    }

    public P domainToResponse(D domain) {
        return modelMapper.map(domain, responseClass);
    }

    public D requestToDomain(R request) {
        return modelMapper.map(request, domainClass);
    }

}