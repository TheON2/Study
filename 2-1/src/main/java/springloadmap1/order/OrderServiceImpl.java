package springloadmap1.order;

import springloadmap1.discount.DiscountPolicy;
import springloadmap1.discount.FixDiscountPolicy;
import springloadmap1.discount.RateDiscountPolicy;
import springloadmap1.member.Member;
import springloadmap1.member.MemberRepository;
import springloadmap1.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
