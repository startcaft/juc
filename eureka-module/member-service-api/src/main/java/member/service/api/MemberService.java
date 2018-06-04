/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/6/1 14:55
 * Description: 会员服务
 */
package member.service.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈会员服务接口定义〉
 *
 * @author StartCaft
 * @create 2018/6/1
 * @since 1.0.0
 */
@RequestMapping("/refactor")
public interface MemberService {

    @GetMapping("/members")
    List<String> getMemebers();

}
