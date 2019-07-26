package com.wy.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wy.demo.entity.User;
import com.wy.demo.mapper.UserMapper;
import com.wy.demo.service.UserService;
import com.wy.demo.vo.UserQueryVo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private UserService service;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Test
	@Transactional
	public void testservice() {

		long start = System.currentTimeMillis();
		List<User> list = new ArrayList<>();
		for (int i = 1; i < 10000; i++) {
			User user = new User();
			user.setId(Long.valueOf(i)).setAge(i).setName(String.valueOf(i)).setBirthDay(LocalDate.now()).setNameDesc(String.valueOf(i));
			list.add(user);
//		User user = User.builder().id(1L).age(1).name("1").birthDay(LocalDate.now()).build();
//			userMapper.insert(user);
		}
		SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
		UserMapper mapper = session.getMapper(UserMapper.class);
		long datetime = System.currentTimeMillis();
		System.out.println("============"+(datetime-start));
		try {
			for (int i = 0;i<list.size();i++) {
				mapper.insert(list.get(i));
				if(i%1000==999 || i==list.size()-1) {
					session.commit();
					session.clearCache();
				}
			}
			System.out.println("============"+(System.currentTimeMillis()-datetime));
		}catch(Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			session.close();
		}
	}

	@Test
	@Transactional
	public void insert() {

		long start = System.currentTimeMillis();
		List<User> list = new ArrayList<>();
		for (int i = 1; i < 10000; i++) {
			User user = new User();
			user.setId(Long.valueOf(i)).setAge(i).setName(String.valueOf(i)).setBirthDay(LocalDate.now()).setNameDesc(String.valueOf(i));
			list.add(user);
//		User user = User.builder().id(1L).age(1).name("1").birthDay(LocalDate.now()).build();
//			userMapper.insert(user);
		}
		service.save(list);
		long end = System.currentTimeMillis();
		System.out.println("============  " + (end-start));
	}

	@Test
	@Transactional
	public void delete(){

		//根据id删除
//		int i1 = userMapper.deleteById(1L);

		//根据条件删除   map多条件连接时  and
//		Map<String, Object> map = Maps.newHashMap();
//		map.put("name_desc","2");
//		map.put("name","2");
//		int i = userMapper.deleteByMap(map);

		//根据id集合删除
//		List<Long> ids  = Lists.newArrayList();
//		ids.add(6L);
//		ids.add(7L);
//		int i = userMapper.deleteBatchIds(ids);


		long start = System.currentTimeMillis();
		int i = userMapper.delete(new QueryWrapper<User>());
		long end = System.currentTimeMillis();
//		int i = userMapper.delete(new QueryWrapper<User>().lambda().like(User::getAge, 1));
//		int i = userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getAge, 11));
//		int i = userMapper.delete(new QueryWrapper<User>().lambda()
//				.in(User::getAge, 12,13));
		System.out.println("================  "+(end-start));
	}

	@Test
	@Transactional
	public void update(){
		User user = new User().setId(1L).setName("upda").setNameDesc("udpat");
//		int i = userMapper.updateById(user);
		//id不动, 其他字段修改
		int i = userMapper.update(user, new QueryWrapper<User>().lambda().lt(User::getId, 24));
		System.out.println("=============    " + i);
	}

	@Test
	public void select(){
		//条件统计
//		User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 20L));


//		List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().eq(false,User::getAge,20).or().eq(User::getAge,40));
//		List<User> users = userMapper.selectList(new QueryWrapper<User>().lambda().gt(User::getAge,20).lt(User::getAge,50));

		Page<User> page = new Page<>(1, 6);

//		IPage<User> iPage = userMapper.selectPage(page, new QueryWrapper<User>().lambda().gt(User::getId,30));
//
//
//		System.out.println(iPage.getRecords().size());
//		System.out.println(iPage.getCurrent());
//		System.out.println(iPage.getPages());
//		System.out.println(iPage.getSize());
//		System.out.println(iPage.getTotal());
//
//		List<User> users = iPage.getRecords();

		UserQueryVo queryVo = new UserQueryVo();
		queryVo.setCurrent(1);
		queryVo.setSize(10);
		queryVo.setAge(1);
		Page<User> users = userMapper.selectUserList(queryVo);
		users.getRecords().stream().forEach(System.out::println);
	}

}
