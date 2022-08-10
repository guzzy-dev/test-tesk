package DAO;

import java.util.List;
import java.util.Optional;

public interface ExpressionDAO<Expression> {
    public Optional<Expression> getById(Long id);
    public List<Expression> getByResult(double result);
    public List<Expression> getWithLowerResults(double result);
    public List<Expression> getWithHigherResults(double result);
    public void save(Expression expression);
}
