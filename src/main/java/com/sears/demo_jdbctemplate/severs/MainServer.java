package com.sears.demo_jdbctemplate.severs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sears.demo_jdbctemplate.models.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


//Repository component for database operations
@Component
public class MainServer {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Insert a new student
    public void save(Student student) {
        String sql = "INSERT INTO students (name, age) VALUES (?, ?)";
        jdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    // Fetch all students
    public List<Student> findAll() {
        String sql = "SELECT * FROM students";
        return jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setAge(rs.getInt("age"));
                return student;
            }
        });
    }

    // Update a student's record
    public void update(Student student) {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        jdbcTemplate.update(sql, student.getName(), student.getAge(), student.getId());
    }

    // Delete a student by ID
    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
