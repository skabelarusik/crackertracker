/**
 * interface for different advice dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface AdviceDao extends TrackerDao {

    boolean insert(String text) throws TrackerDBException;

    boolean deleteById(int id) throws TrackerDBException;

    List<Advice> selectAll() throws TrackerDBException;
}
