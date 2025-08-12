package com.example.remember.report.prompt;

import com.example.remember.RandomWordQuiz.dto.RandomWordRequest;

public final class PromptProvider {
    private PromptProvider() {
    }

    public static String jsonSingleWordPrompt(double memoryScore, double attentionScore, double logicalScore) {
        return """
                당신은 인지능력 평가 전문가입니다. \s
                다음은 사용자의 3개 영역 점수입니다:
                - MEMORY: %.0f
                - ATTENTION: %.0f
                - LOGICAL: %.0f
                
                아래 JSON 형식에 맞춰서만 응답하세요. \s
                주의사항:
                0. date는 현재 날짜를 yyyy-mm-dd로 작성해주세요. 2025-08-13
                1. JSON 외 다른 문구는 절대 포함하지 마세요.
                2. total.score는 세 영역 점수의 평균입니다. total.danger는 아래 표를 기준으로 위험 수준을 반환해주세요.
                4. total.danger는 아래 표를 참고하여 반환하세요. 위험도지수는 100 - 인지능력 점수입니다.\s
                5. total.weak은 3개 영역에서 위험 수준이 높음인 영역들을 반환해주세요. 만약 모두 높음이 아니라면 그 중 가장 낮은 영역을 반환해주세요
                6. total.summary는 전체 상태를 요약하는 문장 2개를 생성하세요.
                7. detail 배열에는 반드시 MEMORY, ATTENTION, LOGICAL 순서로 객체를 작성하세요.
                8. 각 detail 항목에:
                   - scoreDifference: 전날 대비 점수 차이(75)를 계산합니다
                   - location: 모집단 내 위치 (0~100 사이, 임의로 추정)
                   - danger: danger는 100 - score입니다.
                   - danger-level: 아래표를 확인하고 score를 바탕으로 위험수준을 반환해주세요.
                9. advice는 점수와 위험도를 기반으로 생활 조언을 간결하게 작성하세요.
                
                아래는 인지능력 점수 별 위험 지수, 위험 수준 표입니다.
                |   |   |   |
                |---|---|---|
                |인지능력 점수 구간|위험도 지수|위험 수준|
                |81점 이상|0~19점|🟢 낮음|
                |61~80점|20~39점|🟡 중간|
                |31~60점|40~69점|🟠 중간높음|
                |0~30점|70~100점|🔴 높음|
                
                출력 예시:
                {
                 	"date": "2025-08-13",
                 	"total": {
                 		"score": 70.67,
                 		"danger": "중간",
                 		"weak": [
                 			"ATTENTION"
                 		],
                 		"summary": [
                 			"전반적으로 경미한 인지 저하가 관찰되며 전반 점수는 중간 수준입니다.",
                 			"기억력과 논리력은 비교적 양호하지만 집중력은 개선이 필요합니다."
                 		]
                 	},
                 	"detail": [
                 		{
                 			"type": "MEMORY",
                 			"score": 75,
                 			"scoreDifference": 0,
                 			"location": 65,
                 			"danger": 25,
                 			"danger_level": "🟡 중간"
                 		},
                 		{
                 			"type": "ATTENTION",
                 			"score": 60,
                 			"scoreDifference": -15,
                 			"location": 40,
                 			"danger": 40,
                 			"danger_level": "🟠 중간높음"
                 		},
                 		{
                 			"type": "LOGICAL",
                 			"score": 77,
                 			"scoreDifference": 2,
                 			"location": 68,
                 			"danger": 23,
                 			"danger_level": "🟡 중간"
                 		}
                 	],
                 	"advice": "매일 규칙적인 수면(6~8시간)과 가벼운 유산소 운동을 유지하세요. 집중력 향상을 위해 오전 시간에 10~15분간 주의력 훈련(작업 순서 기억, 방해 요소 최소화)을 시행하고, 작업은 짧게 나누어 수행하세요. 기억력과 논리력 유지를 위해 단기 복습(하루 3가지 사건 회상)과 퍼즐·문제 풀이를 주 3~5회 포함하세요."
                 }
                """.formatted(memoryScore, attentionScore, logicalScore);
    }
}
