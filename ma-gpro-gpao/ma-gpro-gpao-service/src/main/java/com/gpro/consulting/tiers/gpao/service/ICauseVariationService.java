package com.gpro.consulting.tiers.gpao.service;

import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = false, rollbackFor = Exception.class)
public interface ICauseVariationService {

}
