import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Arrays;
import java.util.List;

public class JavaToPythonListener implements Java8Listener {
    //METHOD DECLARATION
    @Override
    public void enterMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println();
        System.out.print("def " + ctx.methodHeader().methodDeclarator().Identifier().getText());
    }

    @Override
    public void exitMethodDeclaration(Java8Parser.MethodDeclarationContext ctx) {
        System.out.println();
    }

    @Override
    public void enterMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        System.out.print("(");
    }

    @Override
    public void exitMethodHeader(Java8Parser.MethodHeaderContext ctx) {
        System.out.println("):");
    }

    //METHOD ARGUMENTS DECLARATION
    public void removeTypeParameter(StringBuilder stringBuilder, String formalParameterText, String splitter) {
        List<String> basicTypes = Arrays.asList("int", "double", "float", "short", "long", "byte", "boolean", "char", "String");
        for (String basicType : basicTypes) {
            if(formalParameterText.startsWith(basicType)) {
                stringBuilder.append(formalParameterText.substring(basicType.length()) + splitter);
            }
        }
    }

    @Override
    public void enterFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Java8Parser.FormalParameterContext formalParameterContext: ctx.formalParameters().formalParameter()) {
            removeTypeParameter(stringBuilder, formalParameterContext.getText(), ", ");
        }
        System.out.print(stringBuilder.toString());
    }

    @Override
    public void exitFormalParameterList(Java8Parser.FormalParameterListContext ctx) {
        StringBuilder stringBuilder = new StringBuilder();

        removeTypeParameter(stringBuilder, ctx.lastFormalParameter().formalParameter().getText(), "");

        System.out.print(stringBuilder.toString());
    }

    //VARIABLE DECLARATION
    @Override
    public void enterVariableDeclaratorList(Java8Parser.VariableDeclaratorListContext ctx) {
        System.out.print(ctx.variableDeclarator(0).variableDeclaratorId().getText() + "=");
    }

    @Override
    public void exitVariableDeclaratorList(Java8Parser.VariableDeclaratorListContext ctx) {
        System.out.println();
    }

    //CLASS DECLARATION
    @Override
    public void enterNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {
        System.out.println(ctx.CLASS().getText() + " " + ctx.Identifier().getText() + ":");
    }

    //POST/PRE-INCREMENTATION DECLARATION
    @Override
    public void enterStatementExpression(Java8Parser.StatementExpressionContext ctx) {
        String text = ctx.getText();
        if(text.startsWith("++")){
            System.out.println(text.replace("++", "+=1"));
        } else if(text.startsWith("--")){
            System.out.println(text.replace("--", "-=1"));
        }
    }

    //PRINT DECLARATION
    @Override
    public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        if (ctx.getText().startsWith("System.out.println") || ctx.getText().startsWith("System.out.print")){
            System.out.print("print(");
        }
    }

    @Override
    public void exitMethodInvocation(Java8Parser.MethodInvocationContext ctx) {
        if (ctx.getText().startsWith("System.out.println")){
            System.out.println(")");
        } else if (ctx.getText().startsWith("System.out.print")){
            System.out.println(", end='')");
        }
    }

    //CONDITION STATEMENT DEFINITION
    @Override
    public void enterStatementNoShortIf(Java8Parser.StatementNoShortIfContext ctx) {
        System.out.println(":");
    }

    @Override
    public void enterIfStatement(Java8Parser.IfStatementContext ctx) {
        System.out.println(":");
    }

    @Override
    public void enterElseStatement(Java8Parser.ElseStatementContext ctx) {
        System.out.println("else:");
    }

    @Override
    public void enterIfThenStatement(Java8Parser.IfThenStatementContext ctx) {
        System.out.print("if ");
    }

    @Override
    public void enterIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {
        System.out.print("if ");
    }

    //CONDITION EXPRESSION DEFINITION
    @Override
    public void enterConditionalExpression(Java8Parser.ConditionalExpressionContext ctx) {
        System.out.print(ctx.getText());
    }

    @Override
    public void exitConditionalExpression(Java8Parser.ConditionalExpressionContext ctx) {
        String text = ctx.getText();
        if(text.contains("<") || text.contains(">")) System.out.println("):");
    }

    //FOR-LOOP DECLARATION
    @Override
    public void exitForInit(Java8Parser.ForInitContext ctx) {
        System.out.print("\nwhile(");
    }

    @Override
    public void exitBasicForStatement(Java8Parser.BasicForStatementContext ctx) {
        String forUpdateText = ctx.forUpdate().getText();
        if(forUpdateText.endsWith("++")){
            System.out.println(forUpdateText.replace("++", "+=1"));
        } else if(forUpdateText.endsWith("--")){
            System.out.println(forUpdateText.replace("--", "-=1"));
        } else if (!forUpdateText.contains("++") && !forUpdateText.contains("--")){
            System.out.println(forUpdateText);
        }
        System.out.println();
    }

    //RETURN DEFINITION
    @Override
    public void enterReturnStatement(Java8Parser.ReturnStatementContext ctx) {
        System.out.print("return ");
    }

    @Override
    public void exitReturnStatement(Java8Parser.ReturnStatementContext ctx) {
        System.out.println();
    }



    @Override
    public void enterLiteral(Java8Parser.LiteralContext ctx) {

    }

    @Override
    public void exitLiteral(Java8Parser.LiteralContext ctx) {

    }

    @Override
    public void enterPrimitiveType(Java8Parser.PrimitiveTypeContext ctx) {

    }

    @Override
    public void exitPrimitiveType(Java8Parser.PrimitiveTypeContext ctx) {

    }

    @Override
    public void enterNumericType(Java8Parser.NumericTypeContext ctx) {

    }

    @Override
    public void exitNumericType(Java8Parser.NumericTypeContext ctx) {

    }

    @Override
    public void enterIntegralType(Java8Parser.IntegralTypeContext ctx) {

    }

    @Override
    public void exitIntegralType(Java8Parser.IntegralTypeContext ctx) {

    }

    @Override
    public void enterFloatingPointType(Java8Parser.FloatingPointTypeContext ctx) {

    }

    @Override
    public void exitFloatingPointType(Java8Parser.FloatingPointTypeContext ctx) {

    }

    @Override
    public void enterReferenceType(Java8Parser.ReferenceTypeContext ctx) {

    }

    @Override
    public void exitReferenceType(Java8Parser.ReferenceTypeContext ctx) {

    }

    @Override
    public void enterClassOrInterfaceType(Java8Parser.ClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitClassOrInterfaceType(Java8Parser.ClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterClassType(Java8Parser.ClassTypeContext ctx) {

    }

    @Override
    public void exitClassType(Java8Parser.ClassTypeContext ctx) {

    }

    @Override
    public void enterClassType_lf_classOrInterfaceType(Java8Parser.ClassType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitClassType_lf_classOrInterfaceType(Java8Parser.ClassType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterClassType_lfno_classOrInterfaceType(Java8Parser.ClassType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitClassType_lfno_classOrInterfaceType(Java8Parser.ClassType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType(Java8Parser.InterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType(Java8Parser.InterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType_lf_classOrInterfaceType(Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType_lf_classOrInterfaceType(Java8Parser.InterfaceType_lf_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterInterfaceType_lfno_classOrInterfaceType(Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitInterfaceType_lfno_classOrInterfaceType(Java8Parser.InterfaceType_lfno_classOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterTypeVariable(Java8Parser.TypeVariableContext ctx) {

    }

    @Override
    public void exitTypeVariable(Java8Parser.TypeVariableContext ctx) {

    }

    @Override
    public void enterArrayType(Java8Parser.ArrayTypeContext ctx) {

    }

    @Override
    public void exitArrayType(Java8Parser.ArrayTypeContext ctx) {

    }

    @Override
    public void enterDims(Java8Parser.DimsContext ctx) {

    }

    @Override
    public void exitDims(Java8Parser.DimsContext ctx) {

    }

    @Override
    public void enterTypeParameter(Java8Parser.TypeParameterContext ctx) {

    }

    @Override
    public void exitTypeParameter(Java8Parser.TypeParameterContext ctx) {

    }

    @Override
    public void enterTypeParameterModifier(Java8Parser.TypeParameterModifierContext ctx) {

    }

    @Override
    public void exitTypeParameterModifier(Java8Parser.TypeParameterModifierContext ctx) {

    }

    @Override
    public void enterTypeBound(Java8Parser.TypeBoundContext ctx) {

    }

    @Override
    public void exitTypeBound(Java8Parser.TypeBoundContext ctx) {

    }

    @Override
    public void enterAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {

    }

    @Override
    public void exitAdditionalBound(Java8Parser.AdditionalBoundContext ctx) {

    }

    @Override
    public void enterTypeArguments(Java8Parser.TypeArgumentsContext ctx) {

    }

    @Override
    public void exitTypeArguments(Java8Parser.TypeArgumentsContext ctx) {

    }

    @Override
    public void enterTypeArgumentList(Java8Parser.TypeArgumentListContext ctx) {

    }

    @Override
    public void exitTypeArgumentList(Java8Parser.TypeArgumentListContext ctx) {

    }

    @Override
    public void enterTypeArgument(Java8Parser.TypeArgumentContext ctx) {

    }

    @Override
    public void exitTypeArgument(Java8Parser.TypeArgumentContext ctx) {

    }

    @Override
    public void enterTypeName(Java8Parser.TypeNameContext ctx) {

    }

    @Override
    public void exitTypeName(Java8Parser.TypeNameContext ctx) {

    }

    @Override
    public void enterPackageOrTypeName(Java8Parser.PackageOrTypeNameContext ctx) {

    }

    @Override
    public void exitPackageOrTypeName(Java8Parser.PackageOrTypeNameContext ctx) {

    }

    @Override
    public void enterExpressionName(Java8Parser.ExpressionNameContext ctx) {

    }

    @Override
    public void exitExpressionName(Java8Parser.ExpressionNameContext ctx) {

    }

    @Override
    public void enterMethodName(Java8Parser.MethodNameContext ctx) {

    }

    @Override
    public void exitMethodName(Java8Parser.MethodNameContext ctx) {

    }

    @Override
    public void enterAmbiguousName(Java8Parser.AmbiguousNameContext ctx) {

    }

    @Override
    public void exitAmbiguousName(Java8Parser.AmbiguousNameContext ctx) {

    }

    @Override
    public void enterClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {

    }

    @Override
    public void exitClassDeclaration(Java8Parser.ClassDeclarationContext ctx) {

    }

    @Override
    public void exitNormalClassDeclaration(Java8Parser.NormalClassDeclarationContext ctx) {

    }

    @Override
    public void enterClassModifier(Java8Parser.ClassModifierContext ctx) {

    }

    @Override
    public void exitClassModifier(Java8Parser.ClassModifierContext ctx) {

    }

    @Override
    public void enterTypeParameters(Java8Parser.TypeParametersContext ctx) {

    }

    @Override
    public void exitTypeParameters(Java8Parser.TypeParametersContext ctx) {

    }

    @Override
    public void enterTypeParameterList(Java8Parser.TypeParameterListContext ctx) {

    }

    @Override
    public void exitTypeParameterList(Java8Parser.TypeParameterListContext ctx) {

    }

    @Override
    public void enterClassBody(Java8Parser.ClassBodyContext ctx) {

    }

    @Override
    public void exitClassBody(Java8Parser.ClassBodyContext ctx) {

    }

    @Override
    public void enterClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {

    }

    @Override
    public void exitClassBodyDeclaration(Java8Parser.ClassBodyDeclarationContext ctx) {

    }

    @Override
    public void enterClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {

    }

    @Override
    public void exitClassMemberDeclaration(Java8Parser.ClassMemberDeclarationContext ctx) {

    }

    @Override
    public void enterFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {

    }

    @Override
    public void exitFieldDeclaration(Java8Parser.FieldDeclarationContext ctx) {

    }

    @Override
    public void enterFieldModifier(Java8Parser.FieldModifierContext ctx) {

    }

    @Override
    public void exitFieldModifier(Java8Parser.FieldModifierContext ctx) {

    }

    @Override
    public void enterVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {

    }

    @Override
    public void exitVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {

    }

    @Override
    public void enterVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {

    }

    @Override
    public void exitVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {

    }

    @Override
    public void enterVariableInitializer(Java8Parser.VariableInitializerContext ctx) {

    }

    @Override
    public void exitVariableInitializer(Java8Parser.VariableInitializerContext ctx) {

    }

    @Override
    public void enterUnannType(Java8Parser.UnannTypeContext ctx) {

    }

    @Override
    public void exitUnannType(Java8Parser.UnannTypeContext ctx) {

    }

    @Override
    public void enterUnannPrimitiveType(Java8Parser.UnannPrimitiveTypeContext ctx) {

    }

    @Override
    public void exitUnannPrimitiveType(Java8Parser.UnannPrimitiveTypeContext ctx) {

    }

    @Override
    public void enterUnannReferenceType(Java8Parser.UnannReferenceTypeContext ctx) {

    }

    @Override
    public void exitUnannReferenceType(Java8Parser.UnannReferenceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassOrInterfaceType(Java8Parser.UnannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassOrInterfaceType(Java8Parser.UnannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassType_lf_unannClassOrInterfaceType(Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassType_lf_unannClassOrInterfaceType(Java8Parser.UnannClassType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannClassType_lfno_unannClassOrInterfaceType(Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannClassType_lfno_unannClassOrInterfaceType(Java8Parser.UnannClassType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannInterfaceType_lf_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannInterfaceType_lf_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lf_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannInterfaceType_lfno_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void exitUnannInterfaceType_lfno_unannClassOrInterfaceType(Java8Parser.UnannInterfaceType_lfno_unannClassOrInterfaceTypeContext ctx) {

    }

    @Override
    public void enterUnannTypeVariable(Java8Parser.UnannTypeVariableContext ctx) {

    }

    @Override
    public void exitUnannTypeVariable(Java8Parser.UnannTypeVariableContext ctx) {

    }

    @Override
    public void enterUnannArrayType(Java8Parser.UnannArrayTypeContext ctx) {

    }

    @Override
    public void exitUnannArrayType(Java8Parser.UnannArrayTypeContext ctx) {

    }

    @Override
    public void enterMethodModifier(Java8Parser.MethodModifierContext ctx) {

    }

    @Override
    public void exitMethodModifier(Java8Parser.MethodModifierContext ctx) {

    }

    @Override
    public void enterResult(Java8Parser.ResultContext ctx) {

    }

    @Override
    public void exitResult(Java8Parser.ResultContext ctx) {

    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
    }

    @Override
    public void exitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {

    }

    @Override
    public void enterFormalParameters(Java8Parser.FormalParametersContext ctx) {

    }

    @Override
    public void exitFormalParameters(Java8Parser.FormalParametersContext ctx) {

    }

    @Override
    public void enterFormalParameter(Java8Parser.FormalParameterContext ctx) {

    }

    @Override
    public void exitFormalParameter(Java8Parser.FormalParameterContext ctx) {

    }

    @Override
    public void enterVariableModifier(Java8Parser.VariableModifierContext ctx) {

    }

    @Override
    public void exitVariableModifier(Java8Parser.VariableModifierContext ctx) {

    }

    @Override
    public void enterLastFormalParameter(Java8Parser.LastFormalParameterContext ctx) {

    }

    @Override
    public void exitLastFormalParameter(Java8Parser.LastFormalParameterContext ctx) {
    }

    @Override
    public void enterReceiverParameter(Java8Parser.ReceiverParameterContext ctx) {

    }

    @Override
    public void exitReceiverParameter(Java8Parser.ReceiverParameterContext ctx) {

    }

    @Override
    public void enterMethodBody(Java8Parser.MethodBodyContext ctx) {

    }

    @Override
    public void exitMethodBody(Java8Parser.MethodBodyContext ctx) {

    }

    @Override
    public void enterInstanceInitializer(Java8Parser.InstanceInitializerContext ctx) {

    }

    @Override
    public void exitInstanceInitializer(Java8Parser.InstanceInitializerContext ctx) {

    }

    @Override
    public void enterStaticInitializer(Java8Parser.StaticInitializerContext ctx) {

    }

    @Override
    public void exitStaticInitializer(Java8Parser.StaticInitializerContext ctx) {

    }

    @Override
    public void enterConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {

    }

    @Override
    public void exitConstructorDeclaration(Java8Parser.ConstructorDeclarationContext ctx) {

    }

    @Override
    public void enterConstructorModifier(Java8Parser.ConstructorModifierContext ctx) {

    }

    @Override
    public void exitConstructorModifier(Java8Parser.ConstructorModifierContext ctx) {

    }

    @Override
    public void enterConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {

    }

    @Override
    public void exitConstructorDeclarator(Java8Parser.ConstructorDeclaratorContext ctx) {

    }

    @Override
    public void enterSimpleTypeName(Java8Parser.SimpleTypeNameContext ctx) {

    }

    @Override
    public void exitSimpleTypeName(Java8Parser.SimpleTypeNameContext ctx) {

    }

    @Override
    public void enterConstructorBody(Java8Parser.ConstructorBodyContext ctx) {

    }

    @Override
    public void exitConstructorBody(Java8Parser.ConstructorBodyContext ctx) {

    }

    @Override
    public void enterAnnotation(Java8Parser.AnnotationContext ctx) {

    }

    @Override
    public void exitAnnotation(Java8Parser.AnnotationContext ctx) {

    }

    @Override
    public void enterNormalAnnotation(Java8Parser.NormalAnnotationContext ctx) {

    }

    @Override
    public void exitNormalAnnotation(Java8Parser.NormalAnnotationContext ctx) {

    }

    @Override
    public void enterElementValuePairList(Java8Parser.ElementValuePairListContext ctx) {

    }

    @Override
    public void exitElementValuePairList(Java8Parser.ElementValuePairListContext ctx) {

    }

    @Override
    public void enterElementValuePair(Java8Parser.ElementValuePairContext ctx) {

    }

    @Override
    public void exitElementValuePair(Java8Parser.ElementValuePairContext ctx) {

    }

    @Override
    public void enterElementValue(Java8Parser.ElementValueContext ctx) {

    }

    @Override
    public void exitElementValue(Java8Parser.ElementValueContext ctx) {

    }

    @Override
    public void enterElementValueArrayInitializer(Java8Parser.ElementValueArrayInitializerContext ctx) {

    }

    @Override
    public void exitElementValueArrayInitializer(Java8Parser.ElementValueArrayInitializerContext ctx) {

    }

    @Override
    public void enterElementValueList(Java8Parser.ElementValueListContext ctx) {

    }

    @Override
    public void exitElementValueList(Java8Parser.ElementValueListContext ctx) {

    }

    @Override
    public void enterMarkerAnnotation(Java8Parser.MarkerAnnotationContext ctx) {

    }

    @Override
    public void exitMarkerAnnotation(Java8Parser.MarkerAnnotationContext ctx) {

    }

    @Override
    public void enterSingleElementAnnotation(Java8Parser.SingleElementAnnotationContext ctx) {

    }

    @Override
    public void exitSingleElementAnnotation(Java8Parser.SingleElementAnnotationContext ctx) {

    }

    @Override
    public void enterBlock(Java8Parser.BlockContext ctx) {

    }

    @Override
    public void exitBlock(Java8Parser.BlockContext ctx) {

    }

    @Override
    public void enterBlockStatements(Java8Parser.BlockStatementsContext ctx) {

    }

    @Override
    public void exitBlockStatements(Java8Parser.BlockStatementsContext ctx) {

    }

    @Override
    public void enterBlockStatement(Java8Parser.BlockStatementContext ctx) {

    }

    @Override
    public void exitBlockStatement(Java8Parser.BlockStatementContext ctx) {

    }

    @Override
    public void enterLocalVariableDeclarationStatement(Java8Parser.LocalVariableDeclarationStatementContext ctx) {

    }

    @Override
    public void exitLocalVariableDeclarationStatement(Java8Parser.LocalVariableDeclarationStatementContext ctx) {

    }

    @Override
    public void enterLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {

    }

    @Override
    public void exitLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {

    }

    @Override
    public void enterStatement(Java8Parser.StatementContext ctx) {

    }

    @Override
    public void exitStatement(Java8Parser.StatementContext ctx) {

    }

    @Override
    public void exitStatementNoShortIf(Java8Parser.StatementNoShortIfContext ctx) {

    }

    @Override
    public void enterStatementWithoutTrailingSubstatement(Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {

    }

    @Override
    public void exitStatementWithoutTrailingSubstatement(Java8Parser.StatementWithoutTrailingSubstatementContext ctx) {

    }

    @Override
    public void enterEmptyStatement(Java8Parser.EmptyStatementContext ctx) {

    }

    @Override
    public void exitEmptyStatement(Java8Parser.EmptyStatementContext ctx) {

    }

    @Override
    public void enterLabeledStatement(Java8Parser.LabeledStatementContext ctx) {

    }

    @Override
    public void exitLabeledStatement(Java8Parser.LabeledStatementContext ctx) {

    }

    @Override
    public void enterLabeledStatementNoShortIf(Java8Parser.LabeledStatementNoShortIfContext ctx) {

    }

    @Override
    public void exitLabeledStatementNoShortIf(Java8Parser.LabeledStatementNoShortIfContext ctx) {

    }

    @Override
    public void enterExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {

    }

    @Override
    public void exitExpressionStatement(Java8Parser.ExpressionStatementContext ctx) {
    }

    @Override
    public void exitStatementExpression(Java8Parser.StatementExpressionContext ctx) {

    }

    @Override
    public void exitIfStatement(Java8Parser.IfStatementContext ctx) {
    }

    @Override
    public void exitElseStatement(Java8Parser.ElseStatementContext ctx) {

    }

    @Override
    public void exitIfThenStatement(Java8Parser.IfThenStatementContext ctx) {

    }

    @Override
    public void exitIfThenElseStatement(Java8Parser.IfThenElseStatementContext ctx) {

    }

    @Override
    public void enterForStatement(Java8Parser.ForStatementContext ctx) {

    }

    @Override
    public void exitForStatement(Java8Parser.ForStatementContext ctx) {

    }

    @Override
    public void enterBasicForStatement(Java8Parser.BasicForStatementContext ctx) {

    }

    @Override
    public void enterForInit(Java8Parser.ForInitContext ctx) {

    }

    @Override
    public void enterForUpdate(Java8Parser.ForUpdateContext ctx) {

    }

    @Override
    public void exitForUpdate(Java8Parser.ForUpdateContext ctx) {

    }

    @Override
    public void enterStatementExpressionList(Java8Parser.StatementExpressionListContext ctx) {

    }

    @Override
    public void exitStatementExpressionList(Java8Parser.StatementExpressionListContext ctx) {

    }

    @Override
    public void enterPrimary(Java8Parser.PrimaryContext ctx) {

    }

    @Override
    public void exitPrimary(Java8Parser.PrimaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_arrayAccess(Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_arrayAccess(Java8Parser.PrimaryNoNewArray_lfno_arrayAccessContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lf_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primary(Java8Parser.PrimaryNoNewArray_lf_primary_lfno_arrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lf_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitPrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primary(Java8Parser.PrimaryNoNewArray_lfno_primary_lfno_arrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {

    }

    @Override
    public void exitClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {

    }

    @Override
    public void exitClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {

    }

    @Override
    public void enterClassInstanceCreationExpression_lfno_primary(Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {

    }

    @Override
    public void exitClassInstanceCreationExpression_lfno_primary(Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {

    }

    @Override
    public void enterTypeArgumentsOrDiamond(Java8Parser.TypeArgumentsOrDiamondContext ctx) {

    }

    @Override
    public void exitTypeArgumentsOrDiamond(Java8Parser.TypeArgumentsOrDiamondContext ctx) {

    }

    @Override
    public void enterFieldAccess(Java8Parser.FieldAccessContext ctx) {

    }

    @Override
    public void exitFieldAccess(Java8Parser.FieldAccessContext ctx) {

    }

    @Override
    public void enterFieldAccess_lf_primary(Java8Parser.FieldAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitFieldAccess_lf_primary(Java8Parser.FieldAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterFieldAccess_lfno_primary(Java8Parser.FieldAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitFieldAccess_lfno_primary(Java8Parser.FieldAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterArrayAccess(Java8Parser.ArrayAccessContext ctx) {

    }

    @Override
    public void exitArrayAccess(Java8Parser.ArrayAccessContext ctx) {

    }

    @Override
    public void enterArrayAccess_lf_primary(Java8Parser.ArrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void exitArrayAccess_lf_primary(Java8Parser.ArrayAccess_lf_primaryContext ctx) {

    }

    @Override
    public void enterArrayAccess_lfno_primary(Java8Parser.ArrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void exitArrayAccess_lfno_primary(Java8Parser.ArrayAccess_lfno_primaryContext ctx) {

    }

    @Override
    public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {

    }

    @Override
    public void exitMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {

    }

    @Override
    public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {

    }

    @Override
    public void exitMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {

    }

    @Override
    public void enterArgumentList(Java8Parser.ArgumentListContext ctx) {

    }

    @Override
    public void exitArgumentList(Java8Parser.ArgumentListContext ctx) {

    }

    @Override
    public void enterMethodReference(Java8Parser.MethodReferenceContext ctx) {

    }

    @Override
    public void exitMethodReference(Java8Parser.MethodReferenceContext ctx) {

    }

    @Override
    public void enterMethodReference_lf_primary(Java8Parser.MethodReference_lf_primaryContext ctx) {

    }

    @Override
    public void exitMethodReference_lf_primary(Java8Parser.MethodReference_lf_primaryContext ctx) {

    }

    @Override
    public void enterMethodReference_lfno_primary(Java8Parser.MethodReference_lfno_primaryContext ctx) {

    }

    @Override
    public void exitMethodReference_lfno_primary(Java8Parser.MethodReference_lfno_primaryContext ctx) {

    }

    @Override
    public void enterExpression(Java8Parser.ExpressionContext ctx) {

    }

    @Override
    public void exitExpression(Java8Parser.ExpressionContext ctx) {

    }

    @Override
    public void enterAssignmentExpression(Java8Parser.AssignmentExpressionContext ctx) {

    }

    @Override
    public void exitAssignmentExpression(Java8Parser.AssignmentExpressionContext ctx) {

    }

    @Override
    public void enterAssignment(Java8Parser.AssignmentContext ctx) {

    }

    @Override
    public void exitAssignment(Java8Parser.AssignmentContext ctx) {

    }

    @Override
    public void enterLeftHandSide(Java8Parser.LeftHandSideContext ctx) {

    }

    @Override
    public void exitLeftHandSide(Java8Parser.LeftHandSideContext ctx) {

    }

    @Override
    public void enterAssignmentOperator(Java8Parser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void exitAssignmentOperator(Java8Parser.AssignmentOperatorContext ctx) {

    }

    @Override
    public void enterConditionalOrExpression(Java8Parser.ConditionalOrExpressionContext ctx) {
    }

    @Override
    public void exitConditionalOrExpression(Java8Parser.ConditionalOrExpressionContext ctx) {

    }

    @Override
    public void enterConditionalAndExpression(Java8Parser.ConditionalAndExpressionContext ctx) {

    }

    @Override
    public void exitConditionalAndExpression(Java8Parser.ConditionalAndExpressionContext ctx) {

    }

    @Override
    public void enterInclusiveOrExpression(Java8Parser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitInclusiveOrExpression(Java8Parser.InclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterExclusiveOrExpression(Java8Parser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void exitExclusiveOrExpression(Java8Parser.ExclusiveOrExpressionContext ctx) {

    }

    @Override
    public void enterAndExpression(Java8Parser.AndExpressionContext ctx) {

    }

    @Override
    public void exitAndExpression(Java8Parser.AndExpressionContext ctx) {

    }

    @Override
    public void enterEqualityExpression(Java8Parser.EqualityExpressionContext ctx) {

    }

    @Override
    public void exitEqualityExpression(Java8Parser.EqualityExpressionContext ctx) {

    }

    @Override
    public void enterRelationalExpression(Java8Parser.RelationalExpressionContext ctx) {

    }

    @Override
    public void exitRelationalExpression(Java8Parser.RelationalExpressionContext ctx) {

    }

    @Override
    public void enterShiftExpression(Java8Parser.ShiftExpressionContext ctx) {

    }

    @Override
    public void exitShiftExpression(Java8Parser.ShiftExpressionContext ctx) {

    }

    @Override
    public void enterAdditiveExpression(Java8Parser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void exitAdditiveExpression(Java8Parser.AdditiveExpressionContext ctx) {

    }

    @Override
    public void enterMultiplicativeExpression(Java8Parser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void exitMultiplicativeExpression(Java8Parser.MultiplicativeExpressionContext ctx) {

    }

    @Override
    public void enterUnaryExpression(Java8Parser.UnaryExpressionContext ctx) {

    }

    @Override
    public void exitUnaryExpression(Java8Parser.UnaryExpressionContext ctx) {

    }

    @Override
    public void enterPreIncrementExpression(Java8Parser.PreIncrementExpressionContext ctx) {

    }

    @Override
    public void exitPreIncrementExpression(Java8Parser.PreIncrementExpressionContext ctx) {

    }

    @Override
    public void enterPreDecrementExpression(Java8Parser.PreDecrementExpressionContext ctx) {

    }

    @Override
    public void exitPreDecrementExpression(Java8Parser.PreDecrementExpressionContext ctx) {

    }

    @Override
    public void enterUnaryExpressionNotPlusMinus(Java8Parser.UnaryExpressionNotPlusMinusContext ctx) {

    }

    @Override
    public void exitUnaryExpressionNotPlusMinus(Java8Parser.UnaryExpressionNotPlusMinusContext ctx) {

    }

    @Override
    public void enterPostfixExpression(Java8Parser.PostfixExpressionContext ctx) {

    }

    @Override
    public void exitPostfixExpression(Java8Parser.PostfixExpressionContext ctx) {

    }

    @Override
    public void enterPostIncrementExpression(Java8Parser.PostIncrementExpressionContext ctx) {

    }

    @Override
    public void exitPostIncrementExpression(Java8Parser.PostIncrementExpressionContext ctx) {

    }

    @Override
    public void enterPostIncrementExpression_lf_postfixExpression(Java8Parser.PostIncrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void exitPostIncrementExpression_lf_postfixExpression(Java8Parser.PostIncrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void enterPostDecrementExpression(Java8Parser.PostDecrementExpressionContext ctx) {

    }

    @Override
    public void exitPostDecrementExpression(Java8Parser.PostDecrementExpressionContext ctx) {

    }

    @Override
    public void enterPostDecrementExpression_lf_postfixExpression(Java8Parser.PostDecrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void exitPostDecrementExpression_lf_postfixExpression(Java8Parser.PostDecrementExpression_lf_postfixExpressionContext ctx) {

    }

    @Override
    public void enterCastExpression(Java8Parser.CastExpressionContext ctx) {

    }

    @Override
    public void exitCastExpression(Java8Parser.CastExpressionContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode(ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule(ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule(ParserRuleContext parserRuleContext) {

    }
}
