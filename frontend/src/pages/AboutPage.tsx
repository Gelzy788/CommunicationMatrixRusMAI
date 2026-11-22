import { UserIcon } from 'lucide-react'

import parentWithChildIllustration from '@/assets/parentWithChildIllustration.svg'
import { Card, CardContent } from '@/components/ui/card'

type TeamMember = {
  name: string
  role: string
  photo?: string
}

const team: TeamMember[] = [
  { name: 'Негм Али Абделазизович', role: 'Team Lead and Backend developer', photo: '' },
  { name: 'Королев Лев Игоревич', role: 'Backend developer', photo: '' },
  { name: 'Кривцов Ярослав Олегович', role: 'Backend developer', photo: '' },
  { name: 'Кузмичев Даниил Евгеньевич', role: 'Frontend developer', photo: '' },
  { name: 'Ханукаев Максим Спартакович', role: 'Frontend developer', photo: '' },
]

function Avatar({ member }: { member: TeamMember }) {
  const size = 'mx-auto size-24 shrink-0 rounded-full lg:size-36'

  if (member.photo) {
    return <img src={member.photo} alt={member.name} className={`${size} object-cover`} />
  }

  return (
    <div className={`${size} flex items-center justify-center bg-secondary`}>
      <UserIcon className="size-12 fill-current text-border lg:size-18"></UserIcon>
    </div>
  )
}

export function AboutPage() {
  return (
    <>
      <section className="page-container flex flex-col gap-8 pt-8 lg:flex-row lg:items-start lg:justify-between lg:pt-10">
        <div className="flex max-w-215 flex-col items-start gap-6">
          <span className="rounded-lg bg-secondary px-2 py-2 text-overline text-link">
            Слова · Заботимся о понимании
          </span>
          <h1 className="text-h1 text-foreground">О проекте</h1>
          <h2 className="text-h2 text-foreground">Помогаем родителям замечать развитие ребёнка</h2>
          <div className="flex flex-col gap-5">
            <p className="text-body text-muted-foreground">
              «Слова» — сервис, в котором родители могут пройти «Матрицу коммуникаций» и «Сенсорный
              профиль», сохранить наблюдения за ребёнком и вернуться к результатам позже.
            </p>
            <p className="text-body text-muted-foreground">
              Результаты доступны в личном кабинете: их можно скачать в PDF или отправить
              специалисту. При повторном прохождении матрицы уже освоенные навыки учитываются
              автоматически.
            </p>
          </div>
        </div>

        <div className="flex h-75 w-full items-center justify-center rounded-xl bg-secondary lg:w-72 lg:shrink-0">
          <img src={parentWithChildIllustration} alt="" width={174} height={260} />
        </div>
      </section>

      <section className="page-container flex flex-col gap-6 py-10 lg:pt-12 lg:pb-16">
        <div className="flex flex-col gap-2">
          <h2 className="text-h2 text-foreground">Наша команда</h2>
          <p className="text-body text-muted-foreground">
            Проект разработали пять студентов Московского авиационного института.
          </p>
        </div>
      </section>

      <div className="page-container mb-16">
        <ul className="grid grid-cols-2 gap-4 lg:grid-cols-5 lg:gap-6">
          {team.map((member) => (
            <li key={member.name}>
              <Card className="h-full [--card-spacing:--spacing(3)]">
                <CardContent className="flex flex-col gap-3">
                  <Avatar member={member} />
                  <h3 className="text-h4 text-foreground">{member.name}</h3>
                  <p className="text-small text-muted-foreground">{member.role}</p>
                </CardContent>
              </Card>
            </li>
          ))}
        </ul>
      </div>
    </>
  )
}
