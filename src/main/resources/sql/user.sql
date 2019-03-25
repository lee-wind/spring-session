CREATE TABLE IF NOT EXISTS `user`(
  `user_id` INT UNSIGNED AUTO_INCREMENT,
  `username` VARCHAR(22) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `name` VARCHAR(22),
  `phone_number` VARCHAR(11),
  `ID_Number` VARCHAR(20),
  `avatar` VARCHAR(50),
  `e-mail` VARCHAR(20),
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP,
  `status` TINYINT UNSIGNED DEFAULT 1,
  PRIMARY KEY (`user_id`),
  UNIQUE (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `user` modify `password` VARCHAR(64) NOT NULL;
ALTER TABLE `user` modify `avatar` VARCHAR(100) NOT NULL;
ALTER TABLE `user` ADD UNIQUE

CREATE TABLE IF NOT EXISTS `user_thought`(
  `thought_id` INT UNSIGNED AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `thought_content` MEDIUMTEXT NOT NULL,
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP,
  `status` TINYINT UNSIGNED DEFAULT 1,
  PRIMARY KEY (`thought_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

select username, create_time, update_time
from user
order by create_time desc limit 2 offset 4;

-- dont use if user_id is discontinuous
select username, create_time, update_time
from user
where user_id >=(
  select user_id
  from user
  where user_id > 4
  limit 1)
limit 2;

  <select id="selectByUsernameAndPassword" resultMap="BaseResultMap">
    select user_id, username, password, create_time, update_time
    from user
    where username = #{username,jdbcType=VARCHAR}
    and password = #{password,jdbcType=VARCHAR}
  </select>

  <!--<insert id="insertUniqueUser" parameterType="com.wind.springsession.model.User">-->
    <!--<selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >-->
      <!--SELECT LAST_INSERT_ID()-->
    <!--</selectKey>-->
    <!--insert ignore-->
    <!--into user (username, password)-->
    <!--values (#{username,jdbcType=VARCHAR},-->
      <!--#{password,jdbcType=VARCHAR})-->
  <!--</insert>-->

  <insert id="insertUniqueUser" parameterType="com.wind.springsession.model.User">
    insert ignore
    into user (username, password)
    values (#{username,jdbcType=VARCHAR},
    #{password,jdbcType=VARCHAR})
  </insert>

  <select id="getUserList" resultMap="BaseResultMap">
    select username, create_time, update_time
    from user
    order by create_time desc
    limit #{limit, jdbcType=INTEGER}
    offset #{offset, jdbcType=INTEGER}
  </select>
  <!--<select id="getUserList" resultMap="BaseResultMap">-->
    <!--select username, create_time, update_time-->
    <!--from user-->
    <!--where id >=(-->
      <!--select id-->
      <!--from user-->
      <!--where id > #{offset, jdbcType=INTEGER}-->
      <!--order by id-->
      <!--limit 1)-->
    <!--limit #{limit, jdbcType=INTEGER}-->
  <!--</select>-->

  <insert id="importUserList" parameterType="java.util.List">
    insert ignore into user(username, password)
    values
    <foreach collection="list" index="index" item="item"
      separator=",">
      (#{item.username, jdbcType=VARCHAR},
      #{item.password, jdbcType=VARCHAR})
    </foreach>
  </insert>

  <select id="selectByUsername" resultMap="BaseResultMap">
    select username, password
    from user
    where username = #{username, jdbcType=VARCHAR}
  </select>

  User selectByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int insertUniqueUser(User user);

    List<User> getUserList(@Param("limit") int limit, @Param("offset") int offset);

    int importUserList(List<User> list);

    User selectByUsername(String username);
