package se.iths.rest;


import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.service.StudentService;
import se.iths.service.SubjectService;
import se.iths.exception.Exception;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class  StudentRest {

    StudentService studentService;
    SubjectService subjectService;

    @Inject
    public StudentRest(StudentService studentService,SubjectService subjectService) {
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Path("create")
    @POST
    public Response createStudent(Student student) {

        List<Student> studentsFound = studentService.getAllStudents();
        studentService.createStudent(student);
        String emailValue = student.getEmail();

        if (Exception.findStudentByEmail(studentsFound, emailValue)) {
            Exception.sendJsonEMailException(emailValue);
        }
        return Response.ok(student).build();
    }

    @Path("update")
    @PUT
    public Response updateStudent(Student student) {

        List<Student> studentsFound = studentService.getAllStudents();
        studentService.updateStudent(student);
        String emailValue = student.getEmail();

        if (Exception.findStudentByEmail(studentsFound, emailValue)) {
            Exception.sendEmailException();
        }
        return Response.ok(student).build();
    }

    @Path("getall")
    @GET
    public Response getAllStudents() {
        List<Student> studentsFound = studentService.getAllStudents();
        return Response.ok(studentsFound).build();
    }

    @Path("delete/{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        studentService.deleteStudent(id);
        return Response.noContent().build();
    }

    @Path("{id}")
    @GET
    public Response findStudentById(@PathParam("id") Long id) {
        Student studentFound = studentService.findStudentById(id);
        Exception.studentNotFound(id, studentFound);
        return Response.ok(studentFound).build();

    }
    @Path("findbyLastname")
    @GET
    public Response findStudentByLastname(@QueryParam("findbyLastname") String lastName) {
        List<Student> studentFound = studentService.findByLastname(lastName);
        return Response.ok(studentFound).build();

    }

    @Path("addsubjecttostudent/{studentId}/{subjectId}")
    @PUT
    public Response addSubjectToStudent(@PathParam("studentId") Long studentId, @PathParam("subjectId") Long subjectId) {

        Student studentFound = studentService.findStudentById(studentId);
        Subject subjectFound = subjectService.findBySubjectId(subjectId);
        studentFound.addSubject(subjectFound);
        studentService.updateStudent(studentFound);
        return Response.ok(studentFound).build();
    }


}
