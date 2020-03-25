package io.sjh.jcartadministrationback.es.repo;

import io.sjh.jcartadministrationback.es.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepo extends ElasticsearchRepository<ProductDoc,Integer> {
}
