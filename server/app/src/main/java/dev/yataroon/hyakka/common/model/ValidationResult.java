package dev.yataroon.hyakka.common.model;

import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * * バリデーション結果クラス
 */
@Getter
@AllArgsConstructor
public class ValidationResult {

    /**
     * * バリデーション結果
     */
    private boolean valid;

    /**
     * * バリデーションエラーセット
     */
    private Set<? extends ConstraintViolation<?>> violations;

    /**
     * * バリデーションエラーメッセージをカンマ区切り文字列に
     * 
     * @return
     */
    public String getErrorMessage() {
        if (valid)
            return null;

        return violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
    }
}
