package funzy.variables.solvers;

import java.util.List;

import funzy.variables.memberships.PointMembership;

public interface Solver {
    PointMembership solve(List<PointMembership> points);
}
