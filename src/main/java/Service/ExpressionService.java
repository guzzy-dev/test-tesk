package Service;

import DAO.ExpressionDAO;
import Model.Expression;

import java.util.List;

public class ExpressionService {

    ExpressionDAO<Expression> dao;

    public ExpressionService(ExpressionDAO<Expression> dao) {
        this.dao = dao;
    }

    public Expression getById(Long id){
        return dao.getById(id).get();
    }

    public List<Expression> getByResult(double result){
        return dao.getByResult(result);
    }

    public List<Expression> getWithLowerResults(double result){
        return dao.getWithHigherResults(result);
    }

    public List<Expression> getByHigherResult(double result){
        return dao.getWithLowerResults(result);
    }

    public void save(Expression expression){
        dao.save(expression);
    }
}
