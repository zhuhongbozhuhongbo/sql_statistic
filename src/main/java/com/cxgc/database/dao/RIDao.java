package com.cxgc.database.dao;

import com.cxgc.database.mapper.DBConnectorSingleton;
import com.cxgc.database.mapper.RIMapper;
import com.cxgc.database.model.RailInformation;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RIDao {

    public void add(RailInformation object) throws Exception{
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try{
            RIMapper mapper = sqlSession.getMapper(RIMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        }finally {
            sqlSession.close();
        }
    }


    public List<RailInformation> findByRailId(int railId) throws Exception{
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            RIMapper mapper = sqlSession.getMapper(RIMapper.class);
            return mapper.findByRailId(railId);
        } finally {
            sqlSession.close();
        }
    }


    public void checkResults(int result) {
        if (result > 0) {
            //   System.out.println("processing succeed!");
        } else {
            //   System.out.println("processing failed!");
        }
    }
}
