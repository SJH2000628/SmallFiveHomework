package io.sjh.jcartstoreback.es.repo;

import io.sjh.jcartstoreback.es.doc.ProductDoc;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepo extends ElasticsearchRepository<ProductDoc,Integer> {
    List<ProductDoc> findByProductNameLikeOrProductAbstractLike(String productName, String productAbstract);
}
