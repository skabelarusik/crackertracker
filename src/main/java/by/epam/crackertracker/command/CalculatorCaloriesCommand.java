/**
 * command to calculate calories for different gender and lifestyle activity
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.IdValidator;

import javax.servlet.http.HttpServletRequest;

public class CalculatorCaloriesCommand implements Command {
    private static final double KEF_ACTIVE1 = 1.375;
    private static final double KEF_ACTIVE2 = 1.2;
    private static final double KEF_ACTIVE3 = 1.55;
    private static final double KEF_ACTIVE4 = 1.9;

    private static final int CORRECT_KEF_1 = 10;
    private static final double CORRECT_KEF_2 = 6.25;
    private static final int CORRECT_KEF_3 = 5;
    private static final int CORRECT_KEF_4_MALE = 161;
    private static final int CORRECT_KEF_4_FEMALE = 5;

    @Override
    public String execute(HttpServletRequest request) {
        String age = request.getParameter(ParameterConstant.PARAM_AGE);
        String gender = request.getParameter(ParameterConstant.PARAM_GENDER);
        String active = request.getParameter(ParameterConstant.PARAM_ACTIVE);
        String weight = request.getParameter(ParameterConstant.PARAM_WEIGHT);
        String height = request.getParameter(ParameterConstant.PARAM_HEIGHT);
        String result = checkMessage(age, gender, active, weight, height);
        String page = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        request.setAttribute(ParameterConstant.ATTRIBUTE_RESULT_CALORIES, result);
        return page;
    }

    private String checkMessage(String ageParam, String gender, String active, String weightParam, String heightParam) {
        IdValidator validator = new IdValidator();
        String result = null;
        if (validator.isValidate(ageParam) && validator.isValidate(weightParam) && validator.isValidate(heightParam)) {
            int age = Integer.parseInt(ageParam);
            int weight = Integer.parseInt(weightParam);
            int height = Integer.parseInt(heightParam);
            double kefActivity;
            if (active.equals(ParameterConstant.PARAM_ACT1)) {
                kefActivity = KEF_ACTIVE1;
            } else {
                if (active.equals(ParameterConstant.PARAM_ACT2)) {
                    kefActivity = KEF_ACTIVE2;
                } else {
                    if (active.equals(ParameterConstant.PARAM_ACT3)) {
                        kefActivity = KEF_ACTIVE3;
                    } else {
                        if (active.equals(ParameterConstant.PARAM_ACT4)) {
                            kefActivity = KEF_ACTIVE4;
                        } else {
                            result = ParameterConstant.WRONG_DATA;
                            return result;
                        }
                    }
                }
            }

            if (gender.equals(ParameterConstant.PARAM_FEMALE)) {
                int res = (int)((CORRECT_KEF_1 * weight + CORRECT_KEF_2 * height - CORRECT_KEF_3 * age -
                        CORRECT_KEF_4_MALE)*kefActivity);
                result = String.valueOf(res);
            } else if (gender.equals(ParameterConstant.PARAM_MALE)){
                    int res = (int)((CORRECT_KEF_1 * weight + CORRECT_KEF_2 * height - CORRECT_KEF_3 * age +
                            CORRECT_KEF_4_FEMALE)*kefActivity);
                    result = String.valueOf(res);
                }
            } else {
            result = ParameterConstant.WRONG_DATA;
            }
        return result;
    }
}
