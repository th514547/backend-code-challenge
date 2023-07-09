package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;

    public User user(final Long userId) {
        try {

            return template.queryForObject(
                "select " +
                    "u.id, " +
                    "u.firstName, " +
                    "u.lastName, " +
                    "u.username, " +
                    "u.password, " +
                    "a.* " +
                    "from User u " +
                    "INNER JOIN " +
                    "Address a " +
                    "ON u.addressId=a.id " +
                    "where u.id = ?",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong(1));
                        user.setFirstName(rs.getString(2));
                        user.setLastName(rs.getString(3));
                        user.setUsername(rs.getString(4));
                        user.setPassword(rs.getString(5));
                        Address address = new Address();
                        address.setId(rs.getLong(6));
                        address.setAddress1(rs.getString(7));
                        address.setAddress2(rs.getString(8));
                        address.setCity(rs.getString(9));
                        address.setState(rs.getString(10));
                        address.setPostal(rs.getString(11));
                        user.setAddress(address);
                        return user;
                    }
                },
                userId
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public String updateUser(Long id , User user) {
        String updateQuery = "update User set firstName=? , lastName=? , username=?, password=? where id=?";
        template.update(updateQuery, user.getFirstName() , user.getLastName() , user.getUsername(), user.getPassword() , id);
        return "User with id : " +id+ " updated successfully.";
    }
}
