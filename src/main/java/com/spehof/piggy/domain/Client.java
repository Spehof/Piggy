package com.spehof.piggy.domain;

import com.fasterxml.jackson.annotation.*;
import com.spehof.piggy.exception.*;
import com.spehof.piggy.utils.ClientViews;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Spehof
 * @created 07/04/2021
 */

@Entity
@Table(name = "clients")
@EqualsAndHashCode(of = {"id", "name", "registrationDate"})
@NoArgsConstructor
@Getter
@Setter
public class Client extends BaseEntity {

    public Client(String name, MoneyMovementCategoryHolder moneyMovementCategoryHolder){
        this.name = name;
        this.moneyMovementCategoryHolder = moneyMovementCategoryHolder;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(ClientViews.IdName.class)
    private Long id;

    @Column(name = "name")
    @JsonView(ClientViews.IdName.class)
    private String name;

    @Column(name = "registration_date", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonView(ClientViews.IdNameCreationdate.class)
    private LocalDateTime registrationDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "client")
    @PrimaryKeyJoinColumn
    @JsonBackReference(value = "client-account")
    private Account account;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "client")
    @PrimaryKeyJoinColumn
    @JsonBackReference(value = "client-moneyMovementCategoryHolder")
    private MoneyMovementCategoryHolder moneyMovementCategoryHolder;

    @OneToMany()
    List<MoneyHolder> moneyHolders = new ArrayList<>();

    @OneToMany()
    private List<MoneyHolderType> moneyHolderTypes = new ArrayList<>();

    @OneToMany()
    private List<Friend> friends = new ArrayList<>();

    @OneToMany()
    private List<Budget> budgets = new ArrayList<>();

    @OneToMany()
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany()
    private List<Goal> goals = new ArrayList<>();

    @OneToMany
    private List<Portfolio> portfolios = new ArrayList<>();

    public void setAccount(Account account) {
        //prevent endless loop
        if (this.account != null && sameAsFormer(this.account, account))
            return;
        //set new client account
        Account oldAccount = this.account;
        this.account = account;
        //remove from the old client account
        if (oldAccount!=null)
            oldAccount.setClient(null);
        //set myself into new client account
        if (account!=null)
            account.setClient(this);
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        if (this.registrationDate == null) {
            this.registrationDate = registrationDate;
        }

    }

    public void setMoneyMovementCategoryHolder(MoneyMovementCategoryHolder moneyMovementCategoryHolder) {
        //prevent endless loop
        if (this.moneyMovementCategoryHolder != null && sameAsFormer(this.moneyMovementCategoryHolder, moneyMovementCategoryHolder))
            return;
        //set new client account
        MoneyMovementCategoryHolder oldMoneyMovementCategoryHolder = this.moneyMovementCategoryHolder;
        this.moneyMovementCategoryHolder = moneyMovementCategoryHolder;
        //remove from the old client account
        if (oldMoneyMovementCategoryHolder !=null)
            oldMoneyMovementCategoryHolder.setClient(null);
        //set myself into new client account
        if (moneyMovementCategoryHolder!=null)
            moneyMovementCategoryHolder.setClient(this);
    }


    public void setMoneyHolderTypes(List<MoneyHolderType> moneyHolderTypes){
        for (MoneyHolderType moneyHolderType : moneyHolderTypes) {
            this.setMoneyHolderType(moneyHolderType);
        }
    }

    public void setMoneyHolderType(MoneyHolderType moneyHolderType) {
        //prevent endless loop
        if (this.moneyHolderTypes.contains(moneyHolderType))
            return ;
        //add new earning
        this.moneyHolderTypes.add(moneyHolderType);
        //set myself into the cost account
        moneyHolderType.setClient(this);
    }

    public void setMoneyHolder(MoneyHolder moneyHolder) {
        //prevent endless loop
        if (this.moneyHolders.contains(moneyHolder))
            return ;
        //add new earning
        this.moneyHolders.add(moneyHolder);
        moneyHolder.setClient(this);
    }

    public void setFriends(List<Friend> friends){
        for (Friend friend : friends) {
            this.setFriend(friend);
        }
    }

    public void setFriend(Friend friend) {
        //prevent endless loop
        if (this.friends.contains(friend))
            return ;
        //add new earning
        this.friends.add(friend);
        //set myself into the cost account
        friend.setClient(this);
    }

    public void setBudgets(List<Budget> budgets){
        for (Budget budget : budgets) {
            this.setBudget(budget);
        }
    }

    public void setBudget(Budget budget) {
        //prevent endless loop
        if (this.budgets.contains(budget))
            return ;
        //add new earning
        this.budgets.add(budget);
        //set myself into the cost account
        budget.setClient(this);
    }

    public void setNotifications(List<Notification> notifications){
        for (Notification notification : notifications) {
            this.setNotification(notification);
        }
    }

    public void setNotification(Notification notification) {
        //prevent endless loop
        if (this.notifications.contains(notification))
            return ;
        //add new earning
        this.notifications.add(notification);
        //set myself into the cost account
        notification.setClient(this);
    }

    public void setGoals(List<Goal> goals){
        for (Goal goal : goals) {
            this.setGoal(goal);
        }
    }

    public void setGoal(Goal goal) {
        //prevent endless loop
        if (this.goals.contains(goal))
            return ;
        //add new earning
        this.goals.add(goal);
        //set myself into the cost account
        goal.setClient(this);
    }

    public void removeMoneyHolderType(MoneyHolderType moneyHolderType) {
        //prevent endless loop
        if (!moneyHolderTypes.contains(moneyHolderType))
            return ;
        //remove the account
        moneyHolderTypes.remove(moneyHolderType);
        //remove myself from the twitter account
        moneyHolderType.setClient(null);
    }

    public void removeMoneyHolder(MoneyHolder moneyHolder) {
        //prevent endless loop
        if (!moneyHolders.contains(moneyHolder))
            return ;
        //remove the account
        moneyHolders.remove(moneyHolder);
        moneyHolder.setClient(null);
    }

    public void removeFriend(Friend friend) {
        //prevent endless loop
        if (!friends.contains(friend))
            return ;
        friends.remove(friend);
        friend.setClient(null);
    }

    public void removeBudget(Budget budget) {
        //prevent endless loop
        if (!budgets.contains(budget))
            return ;
        //remove the account
        budgets.remove(budget);
        //remove myself from the twitter account
        budget.setClient(null);
    }

    public void removeNotification(Notification notification) {
        //prevent endless loop
        if (!notifications.contains(notification))
            return ;
        //remove the account
        notifications.remove(notification);
        //remove myself from the twitter account
        notification.setClient(null);
    }

    public void removeGoal(Goal goal) {
        //prevent endless loop
        if (!goals.contains(goal))
            return ;
        //remove the account
        goals.remove(goal);
        //remove myself from the twitter account
        goal.setClient(null);
    }

    public void setPortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (this.portfolios.contains(portfolio))
            return ;
        //add new earning
        this.portfolios.add(portfolio);
        //set myself into the cost account
        portfolio.setClient(this);
    }

    public void removePortfolio(Portfolio portfolio) {
        //prevent endless loop
        if (!portfolios.contains(portfolio))
            return ;
        //remove the account
        portfolios.remove(portfolio);
        //remove myself from the twitter account
        portfolio.setClient(null);
    }

    public Portfolio getPortfolio(Long portfolioId){
        return this.portfolios.stream()
                .filter(portfolio -> portfolio.getId().equals(portfolioId))
                .findFirst()
                .orElseThrow(PortfolioNotFoundException::new);
    }

    public MoneyHolder getMoneyHolder(Long moneyHolderId) {
        return this.moneyHolders.stream()
                .filter(moneyHolder -> moneyHolder.getId().equals(moneyHolderId))
                .findFirst()
                .orElseThrow(MoneyHolderNotFoundException::new);
    }

    public Notification getNotification(Long notificationId){
        return this.notifications.stream()
                .filter(notification -> notification.getId().equals(notificationId))
                .findFirst()
                .orElseThrow(NotificationNotFoundException::new);
    }

    public Friend getFriend(Long friendId){
        return this.friends.stream()
                .filter(friend -> friend.getId().equals(friendId))
                .findFirst()
                .orElseThrow(FriendNotFoundException::new);
    }

    public MoneyHolderType getMoneyHolderType(Long moneyHolderTypeId){
        return this.moneyHolderTypes.stream()
                .filter(moneyHolderType -> moneyHolderType.getId().equals(moneyHolderTypeId))
                .findFirst()
                .orElseThrow(MoneyHolderTypeNotFoundException::new);
    }

    public Goal getGoal(Long oldGoalId) {
        return this.goals.stream()
                .filter(goal -> goal.getId().equals(oldGoalId))
                .findFirst()
                .orElseThrow(GoalNotFoundException::new);
    }

    public Budget getBudget(Long oldBudgetId) {
        return this.budgets.stream()
                .filter(budget -> budget.getId().equals(oldBudgetId))
                .findFirst()
                .orElseThrow(BudgetNotFoundException::new);
    }
}
