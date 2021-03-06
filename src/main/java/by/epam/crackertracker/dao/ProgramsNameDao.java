/**
 * interface for different program name dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.dao;

import by.epam.crackertracker.entity.ProgramsName;
import by.epam.crackertracker.exception.TrackerDBException;

import java.util.List;

public interface ProgramsNameDao extends TrackerDao{

    List<ProgramsName> selectAll(int page, int type) throws TrackerDBException;

    boolean deleteById(int id, int type) throws TrackerDBException;

    boolean insert(ProgramsName name) throws TrackerDBException;

    public boolean updateProgramsName(ProgramsName programsName) throws TrackerDBException;
    }
