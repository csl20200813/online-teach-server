package com.example.eduservice.client;

import com.example.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodFileDegradeFeignClient implements VodClient {
    //熔断器机制，下面的两个方法出错之后才会会执行，不出错的话执行远程调用，去执行vod服务里面的方法
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("熔断器机制成功执行，删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("熔断器机制成功执行，删除多个视频出错了");
    }
}
