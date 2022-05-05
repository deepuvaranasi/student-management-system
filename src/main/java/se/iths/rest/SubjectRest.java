package se.iths.rest;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.Exception;
import se.iths.service.SubjectService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("subjects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SubjectRest {

   SubjectService subjectService;

    @Inject
    public SubjectRest(SubjectService subjectService) {this.subjectService = subjectService;}

    @Path("create")
    @POST

    public Response createSubject(Subject subject) {

        Subject subjectResult = subjectService.createSubject(subject);
        return Response.ok(subjectResult).build();
    }

   @Path("update")
    @PUT
    public Response updateSubject(Subject subject) {
       if (nameExits(subjectService.getAllSubjects(), subject.getSubjectName())) {
           Exception.sendJsonException(subject.getSubjectName());
           return null;
       } else {
           subjectService.updateSubject(subject);
           return Response.ok(subject).build();
       }

    }
    @Path("getall")
    @GET
    public Response getAllSubjects() {
        List<Subject> foundSubjects = subjectService.getAllSubjects();
        return Response.ok(foundSubjects).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteSubject(@PathParam("id") Long id) {
        subjectService.deleteSubject(id);
        return Response.noContent().build();
    }

    @Path("findbyname")
    @GET
    public List<Subject> findSubjectByName(@QueryParam("findbyname") String name) {
        return subjectService.findBySubjectName(name);
    }

    public Subject findSubjectById(Long id) {
        return subjectService.findBySubjectId(id);
    }


    public boolean nameExits(List<Subject> subjectsFound, String nameValue) {
        boolean isPresent = false;

        for (Subject el : subjectsFound) {
            if (el.getSubjectName().contains(nameValue)) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }


}
