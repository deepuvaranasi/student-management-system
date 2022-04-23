package se.iths.rest;


import se.iths.entity.Teacher;
import se.iths.service.TeacherService;
import se.iths.exception.Exception;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("teachers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeacherRest {
    TeacherService teacherService;

    @Inject
    public TeacherRest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Path("create")
    @POST
    public Response createTeacher(Teacher teacher) {

        if (emailExists(teacherService.getAllTeachers(), teacher.getEmail())) {
            Exception.sendJsonEMailException(teacher.getEmail());
            return null;
        } else {
            teacherService.createTeacher(teacher);
            return Response.ok(teacher).build();
        }
    }

    @Path("update")
    @PUT
    public Response updateTeacher(Teacher teacher) {

        if (emailExists(teacherService.getAllTeachers(), teacher.getEmail())) {
            Exception.sendJsonEMailException(teacher.getEmail());
            return null;
        } else {
            teacherService.updateTeacher(teacher);
            return Response.ok(teacher).build();
        }
    }

    @Path("readAll")
    @GET
    public Response getAllTeachers() {
        List<Teacher> foundTeachers = teacherService.getAllTeachers();
        return Response.ok(foundTeachers).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteTeacher(@PathParam("id") Long id) {
        teacherService.deleteTeacher(id);
        return Response.noContent().build();
    }

    public boolean emailExists(List<Teacher> teachersFound, String emailValue) {
        boolean isPresent = false;

        for (Teacher el : teachersFound) {
            if (el.getEmail().contains(emailValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }


    }

