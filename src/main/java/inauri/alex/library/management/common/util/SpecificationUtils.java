package inauri.alex.library.management.common.util;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.metamodel.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collection;

public final class SpecificationUtils {

  public static <T, V> Specification<T> eq(SingularAttribute<T, V> attribute, V value) {
    return (root, query, cb) -> {
      if (value == null) return null;
      return cb.equal(root.get(attribute), value);
    };
  }

  public static <T, V> Specification<T> like(SingularAttribute<T, V> attribute, String value) {
    return (root, query, cb) -> {
      if (value == null || value.isBlank()) {
        return cb.conjunction(); // always true
      }
      Path<String> path = root.get(attribute.getName());
      return cb.like(cb.lower(path), "%" + value.toLowerCase() + "%");
    };
  }

  public static <T, V extends Comparable<? super V>> Specification<T> greaterThan(
      SingularAttribute<T, V> attribute, V value) {
    return (root, query, cb) -> {
      if (value == null) return null;
      return cb.greaterThan(root.get(attribute), value);
    };
  }

  public static <T, V extends Comparable<? super V>> Specification<T> lessThan(
      SingularAttribute<T, V> attribute, V value) {
    return (root, query, cb) -> {
      if (value == null) return null;
      return cb.lessThan(root.get(attribute), value);
    };
  }

  public static <T, V extends Comparable<? super V>> Specification<T> greaterOrEqual(
      SingularAttribute<T, V> attribute, V value) {
    return (root, query, cb) -> {
      if (value == null) return null;
      return cb.greaterThanOrEqualTo(root.get(attribute), value);
    };
  }

  public static <T, V extends Comparable<? super V>> Specification<T> lessOrEqual(
      SingularAttribute<T, V> attribute, V value) {
    return (root, query, cb) -> {
      if (value == null) return null;
      return cb.lessThanOrEqualTo(root.get(attribute), value);
    };
  }

  public static <T, V> Specification<T> in(
      SingularAttribute<T, V> attribute, Collection<V> values) {
    return (root, query, cb) -> {
      if (values == null || values.isEmpty()) return null;
      return root.get(attribute).in(values);
    };
  }

  public static <T, J, V> Specification<T> innerEq(
      SingularAttribute<T, J> joinAttr, SingularAttribute<J, V> targetAttr, V value) {
    return (root, query, cb) -> {
      if (value == null) return cb.conjunction();

      Join<T, J> join = root.join(joinAttr, JoinType.LEFT);
      return cb.equal(join.get(targetAttr), value);
    };
  }

  public static <T, J, V> Specification<T> innerEq(
          ListAttribute<T, J> joinAttr,
          SingularAttribute<J, V> targetAttr,
          V value
  ) {
    return (root, query, cb) -> {
      if (value == null) return cb.conjunction();
      Join<T, J> join = root.join(joinAttr, JoinType.LEFT);
      return cb.equal(join.get(targetAttr), value);
    };
  }

  public static <T, J> Specification<T> fetchJoin(SingularAttribute<T, J> attribute) {
    return (root, query, cb) -> {
      assert query != null;
      if (query.getResultType() != Long.class && query.getResultType() != long.class) {
        root.fetch(attribute, JoinType.LEFT);
        query.distinct(true);
      }
      return cb.conjunction();
    };
  }

  public static <T, J> Specification<T> fetchJoin(SetAttribute<T, J> attribute) {
    return (root, query, cb) -> {
      root.fetch(attribute, JoinType.LEFT);
      assert query != null;
      query.distinct(true);
      return null;
    };
  }

  public static <T, J> Specification<T> fetchJoin(ListAttribute<T, J> attribute) {
    return (root, query, cb) -> {
      root.fetch(attribute, JoinType.LEFT);
      assert query != null;
      query.distinct(true);
      return null;
    };
  }
}
