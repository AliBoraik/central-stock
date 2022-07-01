package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.domain.Specification;
import ru.itis.stockmarket.models.*;
import javax.persistence.criteria.Path;

/**
 * Created by IntelliJ IDEA
 * Date: 16.05.2022
 * Time: 4:29 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc: `Suggestions: maybe add to ProductRepository interface`
 */
public class ProductSpecifications {
    public static Specification<Product> hasCountryCode(String countryCode) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.join(Product_.seller)
                        .join(Organization_.country)
                        .get(Country_.code),
                countryCode);
    }

    public static Specification<Product> hasAtLeastCount(double count) {
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThanOrEqualTo(root.get(Product_.count), count);
    }

    public static Specification<Product> catalogCodeLike(String code) {
        return (root, query, cb) -> {
            Path<String> productCode = root.join(Product_.catalog).get(ProductCatalog_.code);
            return cb.like(productCode, "%"+code+"%");
        };
    }
    public static Specification<Product> catalogCodeEquals(String code) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.join(Product_.catalog)
                        .get(ProductCatalog_.code), code));
    }
}
