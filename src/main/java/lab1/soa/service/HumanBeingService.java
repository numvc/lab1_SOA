package lab1.soa.service;

import lab1.soa.dao.HumanBeingDao;
import lab1.soa.entities.Car;
import lab1.soa.entities.Coordinates;
import lab1.soa.entities.HumanBeing;
import lab1.soa.entities.WeaponType;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class HumanBeingService {
    private final HumanBeingDao humanBeingDao = new HumanBeingDao();
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();

    public HumanBeingService() {
    }

    public HumanBeing findHumanBeing(int id) {
        return humanBeingDao.findById(id);
    }

    public String validateHumanBeing(HumanBeing humanBeing) {
        Set<ConstraintViolation<HumanBeing>> constraintViolations =
                validator.validate(humanBeing);
        StringBuilder message = new StringBuilder();

        if (!constraintViolations.isEmpty())
            for (ConstraintViolation s : constraintViolations)
                message.append(s.getMessage()).append(", ");


        Set<ConstraintViolation<Coordinates>> constraintViolationsCoordinates =
                validator.validate(humanBeing.getCoordinates());
        if (!constraintViolationsCoordinates.isEmpty())
            for (ConstraintViolation s : constraintViolationsCoordinates)
                message.append(s.getMessage()).append(", ");


        Set<ConstraintViolation<Car>> constraintViolationsCar =
                validator.validate(humanBeing.getCar());
        if (!constraintViolationsCar.isEmpty())
            for (ConstraintViolation s : constraintViolationsCar)
                message.append(s.getMessage()).append(", ");


        System.out.println(message);
        if (message.length() < 2)
            return "Done";

        return message.toString();
    }

    @SneakyThrows
    public HumanBeing mapHumanBeing(HttpServletRequest request) {
        HumanBeing newHumanBaing = new HumanBeing();

        System.out.println(request.getParameterMap().toString());

        Car newCar = new Car();
        Coordinates newCoord = new Coordinates();

        newCoord.setX(Integer.parseInt(request.getParameter("x")));
        newCoord.setY(Integer.parseInt(request.getParameter("y")));
        newCar.setName(request.getParameter("car"));

        newHumanBaing.setCar(newCar);
        newHumanBaing.setCoordinates(newCoord);
        newHumanBaing.setName(request.getParameter("name"));
        newHumanBaing.setHasToothpick(Boolean.valueOf(request.getParameter("hasToothpick")));
        newHumanBaing.setImpactSpeed(Double.valueOf(request.getParameter("impactSpeed")));
        newHumanBaing.setMinutesOfWaiting(Double.parseDouble(request.getParameter("minutesOfWaiting")));
        newHumanBaing.setRealHero(Boolean.parseBoolean(request.getParameter("realHero")));
        newHumanBaing.setSoundtrackName(request.getParameter("soundtrackName"));
        newHumanBaing.setWeaponType(WeaponType.valueOf(request.getParameter("weaponType")));

        return newHumanBaing;
    }

    @SneakyThrows
    public void listUser(HttpServletRequest request, HttpServletResponse response) {
        List<HumanBeing> humanBeings = humanBeingDao.findAll();
        int pageSize = humanBeings.size();
        int pageNumber = 1;

        if (request.getParameter("pageSize") != null)
            pageSize = Integer.parseInt(request.getParameter("pageSize"));

        if (request.getParameter("pageNumber") != null)
            pageNumber = Integer.parseInt(request.getParameter("pageNumber"));

        humanBeings = getPage(humanBeings, pageNumber, pageSize);
        // humanBeings = humanBeings.stream().limit(pageSize).collect(Collectors.toList());

        request.setAttribute("human_beings", humanBeings);
        request.setAttribute("pageSize", humanBeings.size());
        request.setAttribute("pageNumber", pageNumber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/human_being.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/action_hb.jsp");
        dispatcher.forward(request, response);
    }

    @SneakyThrows
    public void insertUser(HttpServletRequest request, HttpServletResponse response) {
        HumanBeing newHumanBeing = mapHumanBeing(request);
        String message = validateHumanBeing(newHumanBeing);

        if (!message.equals("Done")) {
            request.setAttribute("message", message);
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/action_hb.jsp");
            dispatcher.forward(request, response);
        } else {
            humanBeingDao.save(newHumanBeing);
            response.sendRedirect("list");
        }
    }


    @SneakyThrows
    public void updateUser(HttpServletRequest request, HttpServletResponse response) {
        HumanBeing newHumanBeing = mapHumanBeing(request);
        String message = validateHumanBeing(newHumanBeing);
        newHumanBeing.setId(Integer.parseInt(request.getParameter("id")));
        newHumanBeing.setCreationDate(findHumanBeing(Integer.parseInt(request.getParameter("id"))).getCreationDate());

        if (!message.equals("Done")) {
            request.setAttribute("message", message);
            request.setAttribute("human_being", newHumanBeing);
            response.setContentType("text/html");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/action_hb.jsp");
            dispatcher.forward(request, response);

        } else {
            System.out.println(newHumanBeing);
            humanBeingDao.update(newHumanBeing);
            response.sendRedirect("list");
        }
    }

    @SneakyThrows
    public void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        humanBeingDao.delete(humanBeingDao.findById(id));

        response.sendRedirect("list");
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HumanBeing existingHumanBeing = humanBeingDao.findById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/action_hb.jsp");
        request.setAttribute("human_being", existingHumanBeing);

        dispatcher.forward(request, response);
    }

    public <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }


}
