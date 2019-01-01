package com.example.nuxtspringboot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/samples")
@RestController
public class SampleResource {
  private final SampleDao sampleDao;

  @Transactional(readOnly = true)
  @ResponseBody
  @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<SampleEntity> all() {

    log.info("fetch all text");
    return sampleDao.selectAll();
  }

  @Transactional
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public void insert(@Validated @RequestBody SampleEntity payload) {
    log.info("store {}", payload);
    sampleDao.insert(payload);
  }
}
